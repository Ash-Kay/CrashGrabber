package io.ashkay.crashgrabber.internal.ui.state

import io.ashkay.crashgrabber.internal.data.entity.CrashLogEntity

data class MainUiState(val crashLogs: List<CrashLogEntity> = emptyList())
