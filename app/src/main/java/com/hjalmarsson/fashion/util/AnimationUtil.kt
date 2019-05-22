package com.hjalmarsson.fashion.util

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Interpolator
import android.view.animation.Transformation
import androidx.core.view.animation.PathInterpolatorCompat

object AnimationUtil {

    private var easeInOutQuart = PathInterpolatorCompat.create(0.77f, 0f, 0.175f, 1f)

    fun expand(view: View, duration: Int, startHeight: Int = 1, endHeight: Int = -1, animInterpolator: Interpolator? = easeInOutQuart, listener: Animation.AnimationListener? = null) {
        val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec((view.parent as View).width, View.MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight = if (endHeight == -1) view.measuredHeight else endHeight

        // Older versions of android (pre API 21) cancel animations for views with a height of 0 so use 1 instead.
        view.layoutParams.height = startHeight
        view.visibility = View.VISIBLE

        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {

                view.layoutParams.height = if (interpolatedTime == 1f) {
                    if (endHeight == -1) ViewGroup.LayoutParams.WRAP_CONTENT else endHeight
                } else
                    (targetHeight * interpolatedTime).toInt()

                view.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        listener?.let {
            animation.setAnimationListener(it)
        }
        animation.interpolator = animInterpolator
        animation.duration = duration.toLong()
        view.startAnimation(animation)
    }

    fun collapse(v: View, duration: Int, animInterpolator: Interpolator? = easeInOutQuart, listener: Animation.AnimationListener? = null) {
        val initialHeight = v.measuredHeight

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        listener?.let {
            a.setAnimationListener(it)
        }
        a.duration = duration.toLong()
        v.startAnimation(a)
    }

    fun objectAnimator(target: View, property: String, value: Float, duration: Long, listener: Animator.AnimatorListener? = null) : ObjectAnimator =
            ObjectAnimator.ofFloat(target, property, value).apply {
                listener?.let { addListener(listener) }
                this.duration = duration
            }

}