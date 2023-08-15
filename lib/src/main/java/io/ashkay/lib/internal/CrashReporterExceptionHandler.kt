package io.ashkay.lib.internal

import io.ashkay.lib.api.CrashGrabber
import java.lang.Thread.UncaughtExceptionHandler

internal class CrashReporterExceptionHandler : UncaughtExceptionHandler {
    private val defaultUncaughtExceptionHandler: UncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        println("Handcaught: ${CrashGrabber.getStackTraceString(throwable)}")
        defaultUncaughtExceptionHandler.uncaughtException(thread, throwable)
    }
}