package io.ashkay.lib.api

import android.app.Application
import android.content.Context
import android.content.Intent
import io.ashkay.lib.internal.data.entity.CrashLogEntity
import io.ashkay.lib.internal.di.CrashGrabberComponent
import io.ashkay.lib.internal.di.DaggerCrashGrabberComponent
import io.ashkay.lib.internal.ui.CrashGrabberMainActivity
import io.ashkay.lib.internal.utils.CrashReporterExceptionHandler
import java.io.PrintWriter
import java.io.StringWriter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object CrashGrabber {
    lateinit var instance: CrashGrabberComponent

    fun init(application: Application) {
        instance = DaggerCrashGrabberComponent.factory().build(application)

        Thread.setDefaultUncaughtExceptionHandler(CrashReporterExceptionHandler { _, throwable ->
            GlobalScope.launch {
                println("ASHTEST: inserting crash log")
                instance.getDao().insertCrashLogEntry(CrashLogEntity(throwable.message.orEmpty()))
            }
        })
    }

    fun getStackTraceString(tr: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        tr.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }

    fun launchActivity(context: Context) {
//        application.startActivity(Intent(CrashGrabberMainActivity::class.java))
        context.startActivity(Intent(context, CrashGrabberMainActivity::class.java))
    }
}