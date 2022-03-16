package com.ibrajix.nftapp.utilis

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.WindowCompat

object Utility {

    fun Activity.setTransparentStatusBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
    }

    fun View.changeVisibility(visibility: Int) {
        val motionLayout = parent as MotionLayout
        motionLayout.constraintSetIds.forEach {
            val constraintSet = motionLayout.getConstraintSet(it) ?: return@forEach
            constraintSet.setVisibility(this.id, visibility)
            constraintSet.applyTo(motionLayout)
        }
    }

}