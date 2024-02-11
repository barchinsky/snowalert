package com.m.snowalert.dashboard.usecase

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.m.snowalert.infrastructure.alert.AlertInfo
import com.m.snowalert.infrastructure.workManager.worker.FetchForecastWorker
import com.m.snowalert.infrastructure.workManager.worker.FetchForecastWorker.Companion.TAG_FETCH_FORECAST_WORKER
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

class EnableSnowAlertUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    suspend fun execute(alertInfo: AlertInfo) =
        withContext(Dispatchers.IO) {
            val workManager = WorkManager.getInstance(context)
            val (notifyAtHour, notifyAtMinutes) = alertInfo.notifyAt.split(":")
                .map { it.toInt() } // "6:00"
            val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) // 22

            val dueDate = Calendar.getInstance()
            dueDate.set(Calendar.HOUR_OF_DAY, notifyAtHour)
            dueDate.set(Calendar.MINUTE, notifyAtMinutes)
            dueDate.add(Calendar.MINUTE, -30) // run job 30 mins before the desired alarm

            if (currentHour > notifyAtHour) {
                dueDate.add(Calendar.HOUR_OF_DAY, 24)
            }

            val delay = dueDate.timeInMillis - Calendar.getInstance().timeInMillis

            println("Due date: ${SimpleDateFormat.getDateTimeInstance().format(dueDate.time)}")

            val fetchForecastWorkRequest = OneTimeWorkRequestBuilder<FetchForecastWorker>()
//            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .addTag(TAG_FETCH_FORECAST_WORKER)
                .build()

            workManager
                .beginUniqueWork(
                    "fetch_forecast_work",
                    ExistingWorkPolicy.REPLACE,
                    fetchForecastWorkRequest
                )
                .enqueue()

            println("Work scheduled.")
        }
}