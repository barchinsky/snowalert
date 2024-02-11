package com.m.snowalert.infrastructure.repository

import com.m.snowalert.infrastructure.alert.AlertInfo

internal interface AlertInfoRepository {

    suspend fun addAlert(alert: AlertInfo): List<AlertInfo>

    suspend fun getAlerts(): List<AlertInfo>
}