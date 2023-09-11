package io.ashkay.lib.internal.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ashkay.lib.internal.data.entity.CrashLogEntity
import io.ashkay.lib.internal.domain.domain.GetCrashByIdUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CrashDetailsViewModel @Inject constructor(
    val getCrashByIdUsecase: GetCrashByIdUsecase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CrashLogEntity?>(null)
    val uiState: StateFlow<CrashLogEntity?> = _uiState.asStateFlow()

    fun getCrashById(id: Int) {
        viewModelScope.launch {
            getCrashByIdUsecase
            val log = getCrashByIdUsecase(id)
            _uiState.emit(log)
        }
    }
}