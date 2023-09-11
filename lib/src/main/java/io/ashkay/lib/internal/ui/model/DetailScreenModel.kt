package io.ashkay.lib.internal.ui.model

data class DetailScreenModel(
    val id: Int,
    val fileName: String,
    val stacktrace: String,
    val timestamp: Long,
    val meta: Map<String, String>,
)