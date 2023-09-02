package io.ashkay.lib.internal.utils

import io.ashkay.lib.api.CrashGrabber
import java.lang.Thread.UncaughtExceptionHandler

internal class CrashReporterExceptionHandler(val callback: (thread: Thread, throwable: Throwable) -> Unit) :
    UncaughtExceptionHandler {
    private val defaultUncaughtExceptionHandler: UncaughtExceptionHandler =
        Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        println("Handcaught: ${CrashGrabber.getStackTraceString(throwable)}")
        callback(thread, throwable)
        Thread.sleep(2000)
        defaultUncaughtExceptionHandler.uncaughtException(thread, throwable)
    }
}