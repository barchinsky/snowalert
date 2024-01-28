package com.m.snowalert.dashboard.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastInfo(
    val snowChance: String,
    val isSnowing: Boolean,
    val forecastDate: String,
    val temperature: String,
): Parcelable
