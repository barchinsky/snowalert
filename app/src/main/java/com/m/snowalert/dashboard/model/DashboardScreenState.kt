package com.m.snowalert.dashboard.model

import com.m.snowalert.infrastructure.alert.AlertDuration
import com.m.snowalert.infrastructure.alert.AlertInfo
import com.m.snowalert.infrastructure.alert.RepeatInterval

data class DashboardScreenState(
    val address: String = "Unknown",
    val forecastInfo: ForecastInfo = ForecastInfo(
        snowChance = "-%",
        isSnowing = false,
        forecastDate = "-.-.-",
        temperature = "- C"
    ),
    val alertInfo: AlertInfo = AlertInfo(
        id = 0,
        enabled = false,
        nextAlertTimestamp = 0,
        repeatInterval = RepeatInterval.BIWEEKLY,
        alertWatchdogDuration = AlertDuration.WEEK,
    )
)
