package com.m.snowalert.dashboard

import com.m.snowalert.dashboard.model.DashboardScreenState
import kotlinx.coroutines.flow.StateFlow

interface DashboardScreenViewModel {

    val state: StateFlow<DashboardScreenState>

    fun onAlertSwitchClick()
}
