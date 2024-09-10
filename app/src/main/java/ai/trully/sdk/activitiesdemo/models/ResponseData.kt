package ai.trully.sdk.activitiesdemo.models

internal class ResponseData private constructor() {
    lateinit var imagesBitmap: ImagesBitmap
    lateinit var label: String

    companion object {
        @Volatile
        private var INSTANCE: ResponseData? = null

        @Synchronized
        fun getInstance(): ResponseData =
            INSTANCE ?: ResponseData().also { INSTANCE = it }
    }

    fun init(imagesBitmap: ImagesBitmap, label: String){
        this.imagesBitmap = imagesBitmap
        this.label = label
    }
}