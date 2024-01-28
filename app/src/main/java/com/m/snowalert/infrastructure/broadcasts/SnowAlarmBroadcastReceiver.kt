package com.m.snowalert.infrastructure.broadcasts

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.impl.utils.ForceStopRunnable.BroadcastReceiver
import com.m.snowalert.MainActivity
import com.m.snowalert.R
import com.m.snowalert.fullscreenalarm.FullscreenAlarmActivity
import java.util.Calendar

@SuppressLint("RestrictedApi")
internal class SnowAlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)

        createNotification(context)
    }

    private fun createNotification(context: Context) {
        val notificationManager = (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        val alarmIntent = Intent(context, FullscreenAlarmActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        val pendingAlarm = PendingIntent.getActivity(context, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val mainIntent = Intent(context, MainActivity::class.java)
        val pendingMain = PendingIntent.getActivity(context, 1, mainIntent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, "snow_alert")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("Snow has fallen!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setFullScreenIntent(pendingAlarm, true)
            .setContentIntent(pendingMain)
            .setOngoing(false)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(Calendar.getInstance().timeInMillis.toInt(), notification)
    }
}