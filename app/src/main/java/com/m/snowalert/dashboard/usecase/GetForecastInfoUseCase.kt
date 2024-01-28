package com.m.snowalert.dashboard.usecase

import com.m.snowalert.dashboard.model.ForecastInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetForecastInfoUseCase @Inject constructor() {

    suspend fun execute(): ForecastInfo =
        withContext(Dispatchers.IO) {
            return@withContext ForecastInfo(
                snowChance = "0%",
                forecastDate = "21.01.2024",
                isSnowing = false,
                temperature = "+1 C"
            )
        }
}