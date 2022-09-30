package com.example.appiot
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    val database = Firebase.database
    val myRef2 = database.getReference("/Station_1/Test/T1")
    val myRef6 = database.getReference("/Station_1/KD")
    val myRef7 = database.getReference("/Station_1/VD")
    val myRef8 = database.getReference("/Station_2/KD2")
    val myRef9 = database.getReference("/Station_2/VD2")
    val myRef5 = database.getReference("/Station_2/Test/T1")
    private val TAG = "FireBaseMessagingService"
    var NOTIFICATION_CHANNEL_ID = "com.example.appiot"
    val NOTIFICATION_ID = 100
    override fun onNewToken(token: String) {
        Log.d("FCMToken","Refresh token:$token")

    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e("message","Message Received ...");

        if (remoteMessage.data.isNotEmpty() ) {
            Log.d("FCMToken","Message data payload:${remoteMessage.data}")
        }
        remoteMessage.notification?.let {
            Log.d("FCMToken","Message Notification Body:${it.body}")
        }
    }
}