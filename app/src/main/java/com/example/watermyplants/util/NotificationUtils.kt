package com.example.watermyplants.util

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import com.example.watermyplants.AlarmReceiver
import java.util.*

class NotificationUtils {

    fun setNotification(timeInMillisSeconds: Long, activity: Activity) {

        val alarmManager = activity.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(activity.applicationContext, AlarmReceiver::class.java)

        alarmIntent.putExtra("reason", "notification")
        alarmIntent.putExtra("timestamp", timeInMillisSeconds)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        val FIFTEEN_SEC_MILLIS = 15000
        val pendingIntent = PendingIntent.getBroadcast(activity, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, FIFTEEN_SEC_MILLIS.toLong(), pendingIntent)
        Log.i("RepeatingAlarm", "Alarm Set")
    }
}