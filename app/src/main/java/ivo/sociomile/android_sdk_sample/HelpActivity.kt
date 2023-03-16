package ivo.sociomile.android_sdk_sample

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ivo.sociomile_sdk_android.Sociomile
import com.ivo.sociomile_sdk_android.SociomileActivity
import ivo.sociomile.android_sdk_sample.MainActivity.Companion.fcmToken
import ivo.sociomile.android_sdk_sample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.help_center_layout.view.*

class HelpActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var btnCallCenter : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.help_center_layout)

        accessSociomile()
    }

    fun accessSociomile(){

        btnCallCenter = findViewById<LinearLayout>(R.id.callCenterBtn)

        btnCallCenter.setOnClickListener { view ->
            val sociomileEngine = Sociomile.Instance()

            sociomileEngine
                .colorSender(0xFFFFFFFF)
                .colorReceiver(0xFF57B77D)
                .colorTheme(0xFF57B77D)
                .colorBackButton(0xFF2B2DE2)
                .colorIconDefault(0xFF6E8597)
                .labelColorSender(0xFF000000)
                .labelColorReceiver(0xFFFFFFFF)
                .labelColorTheme(0xFFC0BEBE)
                .fontFamily("Inter")
                .colorConnectivity(0xFFC03232)
                .lblColorConnectivity(0xFFFFFFFF)
                .screenColor(0xFFF5F5F5)
                .colorTextContainer(0xFFFBFBFB)
                .colorBackgroundAppbar(0xFFFFFFFF)
                .colorAppbarDefault(0xFF000000)
                .lblColorHeaderMsg(0xFF767676)
                .lblColorDateMsg(0xFFB5B8B5)
                .build()

            sociomileEngine
                .build()
                .initialize("BBB", "AAA", "6281288682850", "Zafran")

            sociomileEngine.build().isDarkModeActivated(true)

            sociomileEngine.build().firebaseToken(fcmToken)
            Log.d("FCM", fcmToken)
            sociomileEngine.build().setLogger(true)

            sociomileEngine.build().runSociomileEngine(this)

            startActivity(
                SociomileActivity
                    .withCachedEngineBuilder(Sociomile.FLUTTER_ENGINE_NAME, this)
            )
        }
    }
}