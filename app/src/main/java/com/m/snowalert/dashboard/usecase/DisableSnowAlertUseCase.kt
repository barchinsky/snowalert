package com.m.snowalert.dashboard.usecase

import android.content.Context
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DisableSnowAlertUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun execute() {
        val workManager = WorkManager.getInstance(context)

        workManager.cancelUniqueWork("fetch_forecast_work")
    }
}