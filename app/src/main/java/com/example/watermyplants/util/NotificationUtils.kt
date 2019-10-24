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

//        alarmIntent.action = Intent.ACTION_MAIN
//        alarmIntent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT

        val calendar = Calendar.getInstance()
//        calendar.timeInMillis = timeInMillisSeconds
        calendar.timeInMillis = System.currentTimeMillis()

        val pendingIntent = PendingIntent.getBroadcast(activity, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)

//        val alarmType = AlarmManager.ELAPSED_REALTIME_WAKEUP
        val FIFTEEN_SEC_MILLIS = 15000

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, FIFTEEN_SEC_MILLIS.toLong(), pendingIntent)
//        alarmManager.setRepeating(alarmType, SystemClock.elapsedRealtime() + FIFTEEN_SEC_MILLIS, FIFTEEN_SEC_MILLIS.toLong(), pendingIntent)
        Log.i("RepeatingAlarm", "Alarm Set")
    }
}