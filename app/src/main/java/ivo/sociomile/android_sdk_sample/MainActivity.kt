package ivo.sociomile.android_sdk_sample

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.ivo.sociomile_sdk_android.Sociomile
import com.ivo.sociomile_sdk_android.SociomileActivity
import ivo.sociomile.android_sdk_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    companion object {
        var fcmToken: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                } else {

                }
            }

        askNotificationPermission()

        binding.fab.setOnClickListener { view ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result
                fcmToken = token
                Log.d("FCM_TOKEN", token)

                val sociomileEngine = Sociomile.Builder()
                    .colorSender(0xFF33E05E)
                    .colorOwner(0xFF384191)
                    .colorTheme(0xFF384191)
                    .colorButtonSender(0xFF384191)
                    .colorIconDefault(0xFF384191)
                    .labelColorSender(0xFF389400)
                    .labelColorOwner(0xFF389400)
                    .labelColorTheme(0xFF389400)
                    .fontFamily("Lato")
                    .clientId("AAA")
                    .clientKey("BBB")
                    .userId("6281288682850")
                    .userName("Zafran")
                    .fcmNotificationToken(fcmToken)
                    .build()

                sociomileEngine.runSociomileEngine(this)

                startActivity(
                    SociomileActivity
                        .withCachedEngineBuilder(Sociomile.FLUTTER_ENGINE_NAME, this)
                )
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun askNotificationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                Log.e("MAIN ACTIVITY", "onCreate: PERMISSION GRANTED")
            }
            shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                Snackbar.make(
                    binding.root,
                    "Notification blocked",
                    Snackbar.ANIMATION_MODE_SLIDE
                ).setAction("Settings") {
                    // Responds to click on the action
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    val uri: Uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }.show()
                //  Toast.makeText(this, "NOT ALLOWED", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Log.e("MAIN ACTIVITY", "onCreate: ask for permissions")
                requestPermissionLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }
        }
    }

}