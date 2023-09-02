package io.ashkay.lib.internal.ui

import androidx.lifecycle.ViewModel
import io.ashkay.lib.internal.domain.domain.GetCrashLogsUsecase
import javax.inject.Inject

class CrashGrabberMainViewModel @Inject constructor(
    val getCrashLogsUsecase: GetCrashLogsUsecase
): ViewModel() {

    suspend fun getLogs() {
        getCrashLogsUsecase()
    }

}