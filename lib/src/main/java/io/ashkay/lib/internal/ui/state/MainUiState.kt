package io.ashkay.lib.internal.ui.state

import io.ashkay.lib.internal.data.entity.CrashLogEntity

data class MainUiState(val crashLogs: List<CrashLogEntity> = emptyList())
