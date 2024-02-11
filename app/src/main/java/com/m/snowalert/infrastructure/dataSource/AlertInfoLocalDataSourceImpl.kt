package com.m.snowalert.infrastructure.dataSource

import android.content.SharedPreferences
import com.m.snowalert.infrastructure.alert.AlertInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AlertInfoLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
): AlertInfoLocalDataSource {

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @OptIn(ExperimentalStdlibApi::class)
    private val moshiAdapter: JsonAdapter<List<AlertInfo>> = moshi.adapter<List<AlertInfo>>()

    override suspend fun addAlertInfo(alertInfo: AlertInfo) {
        withContext(Dispatchers.IO) {
            val newAlerts =
                getAlerts().toMutableList()
                    .apply { add(alertInfo) }

            val serializedAlerts = moshiAdapter.toJson(newAlerts)

            sharedPreferences.edit()
                .putString(ALERTS_PREF_KEY, serializedAlerts)
                .apply()
        }
    }

    override suspend fun getAlerts(): List<AlertInfo> =
        withContext(Dispatchers.IO) {
            val serializedAlerts = sharedPreferences.getString(ALERTS_PREF_KEY, "[]")
            val alerts = moshiAdapter.fromJson(serializedAlerts.orEmpty())

            return@withContext alerts.orEmpty()
        }

    private companion object {
        const val ALERTS_PREF_KEY = "pref_key_alerts"
    }
}