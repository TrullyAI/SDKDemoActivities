package ai.trully.sdk.activitiesdemo.models

import android.graphics.Bitmap

internal data class ImagesBitmap(
    val selfie: Bitmap?,
    val doc: Bitmap?,
    val docComplete: Bitmap?,
    val docBack: Bitmap?,
    val docBackComplete: Bitmap?
)
