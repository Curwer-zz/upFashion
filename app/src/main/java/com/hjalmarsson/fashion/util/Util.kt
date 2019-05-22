package com.hjalmarsson.fashion.util

import android.content.Context
import android.util.DisplayMetrics

object Util {

    fun getStatusBarHeight(context: Context): Int {
        return getHeightForResource(context, "status_bar_height")
    }

    fun getNavigationBarHeight(context: Context): Int {
        return getHeightForResource(context, "navigation_bar_height")
    }

    private fun getHeightForResource(context: Context, name: String): Int {
        val resId = context.resources.getIdentifier(name, "dimen", "android")
        if (resId > 0) {
            return getDimensionSize(context, resId)
        } else {
            return 0
        }
    }

    private fun getDimensionSize(context: Context, resId: Int): Int {
        return context.resources.getDimensionPixelSize(resId)
    }

    fun convertDpToPixel(context: Context, dp: Float): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun convertPixelsToDp(context: Context, px: Float): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}
