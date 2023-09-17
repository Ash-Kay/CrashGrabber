package io.ashkay.crashgrabber.internal.utils

import java.lang.Thread.UncaughtExceptionHandler

internal class CrashReporterExceptionHandler(val callback: (thread: Thread, throwable: Throwable) -> Unit) :
    UncaughtExceptionHandler {
    private val defaultUncaughtExceptionHandler: UncaughtExceptionHandler? =
        Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        callback(thread, throwable)
        defaultUncaughtExceptionHandler?.uncaughtException(thread, throwable)
    }
}