package io.ashkay.lib.api

import io.ashkay.lib.internal.CrashReporterExceptionHandler
import java.io.PrintWriter
import java.io.StringWriter

object CrashGrabber {
    fun init() {
        Thread.setDefaultUncaughtExceptionHandler(CrashReporterExceptionHandler())
    }

    fun getStackTraceString(tr: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        tr.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }
}