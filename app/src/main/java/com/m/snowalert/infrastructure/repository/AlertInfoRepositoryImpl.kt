package com.m.snowalert.infrastructure.repository

import com.m.snowalert.infrastructure.alert.AlertInfo
import com.m.snowalert.infrastructure.dataSource.AlertInfoLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AlertInfoRepositoryImpl @Inject constructor(
    private val localAlertInfoDataSource: AlertInfoLocalDataSource,
): AlertInfoRepository {

    override suspend fun addAlert(alert: AlertInfo): List<AlertInfo> =
        localAlertInfoDataSource.let {
                it.addAlertInfo(alert)
                it.getAlerts()
            }

    override suspend fun getAlerts(): List<AlertInfo> =
        localAlertInfoDataSource.getAlerts()
}