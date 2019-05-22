package com.hjalmarsson.fashion.misc

import android.animation.Animator

abstract class MyAnimatorListener : Animator.AnimatorListener {
    override fun onAnimationCancel(animation: Animator?) { }

    override fun onAnimationEnd(animation: Animator?) { }

    override fun onAnimationRepeat(animation: Animator?) { }

    override fun onAnimationStart(animation: Animator?) { }
}