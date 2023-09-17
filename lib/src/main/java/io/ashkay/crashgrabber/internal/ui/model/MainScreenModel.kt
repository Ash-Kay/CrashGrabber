package io.ashkay.crashgrabber.internal.ui.model

data class MainScreenModel(
    val id: Int,
    val fileName: String,
    val message: String,
    val timestamp: Long,
)