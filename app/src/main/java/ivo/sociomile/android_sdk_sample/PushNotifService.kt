package ivo.sociomile.android_sdk_sample

import android.util.Log
import androidx.core.app.TaskStackBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ivo.sociomile_sdk_android.Sociomile
import com.ivo.sociomile_sdk_android.SociomileActivity
import com.ivo.sociomile_sdk_android.showNotification

class PushNotifService : FirebaseMessagingService(){
    override fun onNewToken(token: String) {
        Log.i("SellerFirebaseService ", "Refreshed token :: $token")
        MainActivity.fcmToken = token
        super.onNewToken(token)
    }
    //    <uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.getNotification() != null) {

            val intent = SociomileActivity
                .withCachedEngineBuilder(Sociomile.FLUTTER_ENGINE_NAME, this)

            val stackBuilder = TaskStackBuilder.create(this)
                .addParentStack(MainActivity::class.java)
                .addNextIntent(intent)

            showNotification(
                context = this,
                title = remoteMessage.notification?.title.toString(),
                desc = remoteMessage.notification?.body.toString(),
                iconNotification = R.mipmap.ic_launcher,
                mapData = remoteMessage.data,
                stackBuilder = stackBuilder
            )
        }
    }
}