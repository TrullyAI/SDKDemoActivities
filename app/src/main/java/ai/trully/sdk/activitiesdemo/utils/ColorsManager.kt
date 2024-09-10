package ai.trully.sdk.activitiesdemo.utils

import ai.trully.sdk.activitiesdemo.R
import android.app.Activity
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

internal class ColorsManager(activity: Activity) {
    private val activityRef = WeakReference(activity)
    private val primaryColor = R.color.primary

    private fun Activity.setActivityStatusBarColor(colorResId: Int) {
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, colorResId)
    }

    private fun setStatusBarColor() {
        activityRef.get()?.setActivityStatusBarColor(primaryColor)
    }

    fun applyColors() {
        setStatusBarColor()
    }
}