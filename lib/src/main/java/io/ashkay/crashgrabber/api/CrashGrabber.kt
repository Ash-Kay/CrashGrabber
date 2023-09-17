package io.ashkay.crashgrabber.api

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.util.Log
import androidx.core.content.getSystemService
import io.ashkay.crashgrabber.R
import io.ashkay.crashgrabber.internal.data.entity.CrashLogEntity
import io.ashkay.crashgrabber.internal.di.CrashGrabberComponent
import io.ashkay.crashgrabber.internal.di.DaggerCrashGrabberComponent
import io.ashkay.crashgrabber.internal.ui.screen.main.CrashGrabberMainActivity
import io.ashkay.crashgrabber.internal.ui.screen.main.substringOrFull
import io.ashkay.crashgrabber.internal.utils.CrashReporterExceptionHandler
import io.ashkay.crashgrabber.internal.utils.getDeviceMetaAsJson
import java.io.PrintWriter
import java.io.StringWriter
import kotlinx.coroutines.runBlocking

object CrashGrabber {
    private var instance: CrashGrabberComponent? = null

    private const val SHORTCUT_ID = "io.ashkay.crashgrabber"

    fun getOrCreate(context: Context): CrashGrabberComponent {
        instance?.let {
            return it
        }

        DaggerCrashGrabberComponent.factory().build(context).also { instance ->
            registerUncaughtExceptionHandler(instance)
            this.instance = instance
            return instance
        }
    }

    private fun registerUncaughtExceptionHandler(instance: CrashGrabberComponent) {
        Thread.setDefaultUncaughtExceptionHandler(CrashReporterExceptionHandler { _, throwable ->
            runBlocking {
                val stackTrace = getStackTraceString(throwable)
                instance.getDao().insertCrashLogEntry(
                    CrashLogEntity(
                        fileName = throwable.stackTrace[0].fileName, //TODO: check if can crash
                        message = throwable.message ?: stackTrace.substringOrFull(50),
                        stacktrace = stackTrace,
                        timestamp = System.currentTimeMillis(),
                        meta = getDeviceMetaAsJson()
                    )
                )
            }
        })
    }

    fun clear() {
        val exceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler)
    }

    fun getStackTraceString(tr: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        tr.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }

    /**
     * Create a shortcut to launch CrashGrabber UI.
     * @param context An Android [Context].
     */
    fun createShortcut(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) {
            return
        }

        val shortcutManager = context.getSystemService<ShortcutManager>() ?: return
        if (shortcutManager.dynamicShortcuts.any { it.id == SHORTCUT_ID }) {
            return
        }

        val shortcut = ShortcutInfo.Builder(context, SHORTCUT_ID)
            .setShortLabel(context.getString(R.string.shortcut_label))
            .setLongLabel(context.getString(R.string.shortcut_label))
            .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher_crashgrabber))
            .setIntent(getLaunchIntent(context).setAction(Intent.ACTION_VIEW))
            .build()
        try {
            shortcutManager.addDynamicShortcuts(listOf(shortcut))
        } catch (e: IllegalArgumentException) {
            Log.e("CrashGrabber", "ShortcutManager addDynamicShortcuts failed ", e)
        } catch (e: IllegalStateException) {
            Log.e("CrashGrabber", "ShortcutManager addDynamicShortcuts failed ", e)
        }
    }

    /**
     * Get an Intent to launch the CrashGrabber UI directly.
     * @param context An Android [Context].
     * @return An Intent for the main CrashGrabber Activity that can be started with [Context.startActivity].
     */
    @JvmStatic
    fun getLaunchIntent(context: Context): Intent {
        return Intent(context, CrashGrabberMainActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}