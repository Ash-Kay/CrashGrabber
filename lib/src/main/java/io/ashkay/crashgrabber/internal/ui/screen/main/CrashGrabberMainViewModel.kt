package io.ashkay.crashgrabber.internal.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ashkay.crashgrabber.internal.domain.domain.GetCrashLogsUsecase
import io.ashkay.crashgrabber.internal.ui.state.MainUiState
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