package io.ashkay.lib.internal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ashkay.lib.internal.domain.domain.GetCrashLogsUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CrashGrabberMainViewModel @Inject constructor(
    val getCrashLogsUsecase: GetCrashLogsUsecase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        getCrashLogs()
    }

    fun getCrashLogs() {
        viewModelScope.launch {
            val logs = getCrashLogsUsecase()
            _uiState.emit(_uiState.value.copy(crashLogs = logs))
        }
    }

}