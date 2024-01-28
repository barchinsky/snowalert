package com.m.snowalert.infrastructure.workManager.worker

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.AlarmManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.m.snowalert.dashboard.usecase.FetchForecastInfoUseCase
import com.m.snowalert.dashboard.usecase.GetForecastInfoUseCase
import com.m.snowalert.infrastructure.broadcasts.SnowAlarmBroadcastReceiver
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.Calendar

@HiltWorker
class FetchForecastWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val fetchForecastInfoUseCase: FetchForecastInfoUseCase,
    private val getForecastInfoUseCase: GetForecastInfoUseCase,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        println("Fetching forecast")
        createNotificationChannel()

        fetchForecastInfoUseCase.execute()

        val forecastInfo = getForecastInfoUseCase.execute()

        // TODO set alarm if needed
        println("Forecast info: $forecastInfo")

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmDate = Calendar.getInstance()
        alarmDate.add(Calendar.SECOND, 10)

        val alarmIntent = Intent(
            context,
            SnowAlarmBroadcastReceiver::class.java
        ).apply {
            action = "com.snowalert.TRIGGER_ALERT"
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            alarmIntent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        AlarmManagerCompat.setExactAndAllowWhileIdle(alarmManager, RTC_WAKEUP, alarmDate.timeInMillis, pendingIntent)

        return Result.success()
    }

    private fun createNotificationChannel() {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(
            "snow_alert",
            "Snow alert channel",
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.createNotificationChannel(notificationChannel)
    }

    companion object {
        const val TAG_FETCH_FORECAST_WORKER = "fetch_forecast_worker_tag"
    }
}