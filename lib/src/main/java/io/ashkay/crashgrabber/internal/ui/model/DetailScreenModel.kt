package io.ashkay.crashgrabber.internal.ui.model

internal data class DetailScreenModel(
    val id: Int,
    val fileName: String,
    val stacktrace: String,
    val timestamp: Long,
    val meta: Map<String, String>,
)