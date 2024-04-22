package com.nc248radio.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.LinearLayout

object AnimationUtils {

    fun expandToWrap(view: View) {
        view.measure(LinearLayout.LayoutParams.MATCH_PARENT, View.MeasureSpec.UNSPECIFIED)
        val targetHeight = view.measuredHeight
        val initialHeight = view.height

        val anim = ValueAnimator.ofInt(initialHeight, targetHeight)
        anim.interpolator = AccelerateInterpolator()
        anim.duration = 300 //Milliseconds
        anim.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            view.layoutParams.height = value
            view.requestLayout()

        }
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val layoutParams = view.layoutParams
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        })
        anim.start()
    }

    fun collapseToHeight(view: View, height: Float) {
        val targetHeight = height.toInt()
        val initialHeight = view.height

        val anim = ValueAnimator.ofInt(initialHeight, targetHeight)
        anim.interpolator = AccelerateInterpolator()
        anim.duration = 300 //Milliseconds
        anim.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            view.layoutParams.height = value
            view.requestLayout()

        }
        anim.start()
    }

}