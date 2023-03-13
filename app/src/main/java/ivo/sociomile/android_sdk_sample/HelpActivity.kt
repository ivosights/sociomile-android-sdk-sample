package ivo.sociomile.android_sdk_sample

import android.os.Bundle
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
                .colorSender(0xFF33E05E)
                .colorReceiver(0xFF384191)
                .colorTheme(0xFF384191)
                .colorButtonSender(0xFF384191)
                .colorIconDefault(0xFF384191)
                .labelColorSender(0xFF389400)
                .labelColorReceiver(0xFF389400)
                .labelColorTheme(0xFF389400)
                .fontFamily("Lato")
                .colorConnectivity(0xFF389400)
                .lblColorConnectivity(0xFFFFFFFF)
                .screenColor(0xFFDFDFDF)
                .colorTextContainer(0xFF384191)
                .colorBackgroundAppbar(0xFF384191)
                .colorAppbarDefault(0xFFFFFFFF)
                .lblColorHeaderMsg(0xFF33E05E)
                .lblColorDateMsg(0xFF33E05E)
                .build()

            sociomileEngine
                .build()
                .initialize("BBB", "AAA", "6281288682850", "Zafran")

            sociomileEngine.build().isDarkModeActivated(true)

            sociomileEngine.build().firebaseToken(fcmToken)

            sociomileEngine.build().setLogger(true)

            sociomileEngine.build().runSociomileEngine(this)

            startActivity(
                SociomileActivity
                    .withCachedEngineBuilder(Sociomile.FLUTTER_ENGINE_NAME, this)
            )
        }
    }
}