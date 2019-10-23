package com.example.watermyplants.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.watermyplants.MainActivity

object NotificationGenerator {
    fun waterNotification(context: Context) {

        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingNotificationIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT)
        val channelID = "${context.packageName}.notificationchannel"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Water Notification Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val description = "The Water Notification Channel is working"

            val getID = NotificationChannel (channelID, name, importance)
            getID.description = description

            notificationManager.createNotificationChannel(getID)
        }

        val notificationBuilder = NotificationCompat.Builder(context, channelID)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setContentTitle("Water Notification")
            .setContentText("Testing the Water Notification")
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .setColor(Color.DKGRAY)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentIntent(pendingNotificationIntent)
            .setAutoCancel(true)
        notificationManager.notify(MainActivity.NOTIFICATION_ID_INSTANT, notificationBuilder.build())
    }
}