package com.m.snowalert.dashboard.model

data class DashboardScreenState(
    val address: String = "Unknown",
    val forecastInfo: ForecastInfo = ForecastInfo(
        snowChance = "-%",
        isSnowing = false,
        forecastDate = "-.-.-",
        temperature = "- C"
    ),
    val alertInfo: AlertInfo = AlertInfo(
        enabled = false,
        firstAlertDay = 0,
        notifyAt = "7:00",
        nextAlertInMinutes = 0,
        repeatInterval = AlertInfo.RepeatInterval.BIWEEKLY,
        alertWatchdogDuration = AlertInfo.AlertDuration.WEEK,
    )
)
