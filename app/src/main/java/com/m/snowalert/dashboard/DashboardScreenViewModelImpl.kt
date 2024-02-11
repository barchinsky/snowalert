package com.m.snowalert.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m.snowalert.dashboard.model.DashboardScreenState
import com.m.snowalert.dashboard.usecase.DisableSnowAlertUseCase
import com.m.snowalert.dashboard.usecase.EnableSnowAlertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DashboardScreenViewModelImpl @Inject constructor(
    private val enableSnowAlertUseCase: EnableSnowAlertUseCase,
    private val disableSnowAlertUseCase: DisableSnowAlertUseCase,
) : DashboardScreenViewModel, ViewModel() {

    override val state = MutableStateFlow(DashboardScreenState())

    override fun onAlertSwitchClick() {
        val isAlertEnabled = state.value.alertInfo.enabled
        val newAlertEnabledValue = !isAlertEnabled

        if (!newAlertEnabledValue) {
            disableSnowAlertUseCase.execute()
        } else {
            viewModelScope.launch {
                runCatching {
                    enableSnowAlertUseCase.execute(state.value.alertInfo)
                }.onFailure {
                    println("Failed to enable snow alert: $it")
                    state.update { state -> state.copy(alertInfo = state.alertInfo.copy(enabled = isAlertEnabled)) }
                }
            }
        }
        state.update { it.copy(alertInfo = it.alertInfo.copy(enabled = newAlertEnabledValue)) }
    }
}