package ai.trully.sdk.activitiesdemo.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import ai.trully.sdk.TrullySdk
import ai.trully.sdk.activitiesdemo.databinding.ActivityMainBinding
import ai.trully.sdk.activitiesdemo.models.ImagesBitmap
import ai.trully.sdk.activitiesdemo.models.ResponseData
import ai.trully.sdk.activitiesdemo.utils.ColorsManager
import ai.trully.sdk.configurations.TrullyConfig
import ai.trully.sdk.models.Environment
import ai.trully.sdk.models.ErrorData
import ai.trully.sdk.models.TrackDetail
import ai.trully.sdk.models.TrackStep
import ai.trully.sdk.models.TrullyResponse
import ai.trully.sdk.protocols.listeners.TrullyListeners
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log

class MainActivity : AppCompatActivity(), TrullyListeners {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        initListeners()
    }

    private fun initUI() {
        ColorsManager(this).applyColors()
    }

    private fun initListeners() {
        binding.launchBtn.setOnClickListener {
            runTrullyKotlinSDK()
        }
    }

    override fun onResult(response: TrullyResponse) {
        val selfie = response.images?.selfieStr?.let { base64ToBitmap(it) }
        val doc = response.images?.documentStr?.let { base64ToBitmap(it) }
        val docComplete = response.images?.documentCompleteStr?.let { base64ToBitmap(it) }
        val docBack = response.images?.documentBackStr?.let { base64ToBitmap(it) }
        val docBackComplete = response.images?.documentBackCompleteStr?.let { base64ToBitmap(it) }

        val imagesBitmap = ImagesBitmap(
            selfie,
            doc,
            docComplete,
            docBack,
            docBackComplete
        )

        val label: String = response.shortResponse?.label ?: ""

        ResponseData.getInstance().init(
            imagesBitmap, label
        )

        val intent = Intent(this, ResultActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
    }

    override fun onTrack(trackStep: TrackStep) {
        Log.d("onTrack", trackStep.toString())
    }

    override fun onTrackDetail(trackDetail: TrackDetail) {
        Log.d("onTrackDetail", trackDetail.toString())
    }

    override fun onError(errorData: ErrorData) {
        Log.d("onError", errorData.toString())
    }

    private fun runTrullyKotlinSDK() {
        val config = TrullyConfig(environment = Environment.DEBUG, userID = "TRULLYKOTLINSDK_DEMO_ACTIVITIES")
        TrullySdk.init(apiKey = "9zODjQTn8G5QiyKy1swg3aehp3x96zgQ7MNbQq3z", config = config)
        TrullySdk.start(packageContext = this, listener = this)
    }

    private fun base64ToBitmap(base64Str: String): Bitmap? {
        val imageBytes = Base64.decode(base64Str, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}