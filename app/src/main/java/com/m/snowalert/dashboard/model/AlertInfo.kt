package com.m.snowalert.dashboard.model

data class AlertInfo(
    val enabled: Boolean,
    val firstAlertDay: Int, // "21.01.2024
    val notifyAt: String, // "6:00" or "18:00"
    val nextAlertInMinutes: Int,
    val repeatInterval: RepeatInterval,
    val alertWatchdogDuration: AlertDuration,
) {

    enum class RepeatInterval {
        DAILY,
        WEEKLY,
        BIWEEKLY
    }

    enum class AlertDuration {
        DAY,
        WEEK,
        MONTH,
    }
}
