package com.m.snowalert.infrastructure.alert

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlertInfo(
    val id: Int,
    val enabled: Boolean,
    val nextAlertTimestamp: Int,
    val repeatInterval: RepeatInterval,
    val alertWatchdogDuration: AlertDuration,
)

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
