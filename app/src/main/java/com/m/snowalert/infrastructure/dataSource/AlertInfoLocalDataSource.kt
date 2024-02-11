package com.m.snowalert.infrastructure.dataSource

import com.m.snowalert.infrastructure.alert.AlertInfo

internal interface AlertInfoLocalDataSource {

    suspend fun addAlertInfo(alertInfo: AlertInfo)

    suspend fun getAlerts(): List<AlertInfo>
}