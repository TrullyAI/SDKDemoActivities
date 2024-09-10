package ai.trully.sdk.activitiesdemo.views

import ai.trully.sdk.activitiesdemo.databinding.ActivityResultBinding
import ai.trully.sdk.activitiesdemo.models.ResponseData
import ai.trully.sdk.activitiesdemo.utils.ColorsManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val responseData = ResponseData.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        initListeners()
    }

    private fun initUI() {
        ColorsManager(this).applyColors()
        setImages()
        setLabel()
    }

    private fun initListeners() {
        binding.restartBtn.setOnClickListener {
            restartDemo()
        }
    }

    private fun setImages() {
        val selfieImg = responseData.imagesBitmap.selfie
        val docImg = responseData.imagesBitmap.doc
        val docCompleteImg = responseData.imagesBitmap.docComplete
        val docBackImg = responseData.imagesBitmap.docBack
        val docBackCompleteImg = responseData.imagesBitmap.docBackComplete

        if(selfieImg != null) binding.ivSelfie.setImageBitmap(selfieImg)
        if(docImg != null) binding.ivDoc.setImageBitmap(docImg)
        if(docCompleteImg != null) binding.ivDocComplete.setImageBitmap(docCompleteImg)
        if(docBackImg != null) binding.ivDocBack.setImageBitmap(docBackImg)
        if(docBackCompleteImg != null) binding.ivDocBackComplete.setImageBitmap(docBackCompleteImg)
    }

    private fun setLabel(){
        val label = responseData.label
        binding.tvLabel.text = label
    }

    private fun restartDemo() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }
}