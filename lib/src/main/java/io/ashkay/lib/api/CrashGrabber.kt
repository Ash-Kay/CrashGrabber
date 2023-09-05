package io.ashkay.lib.api

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.util.Log
import androidx.core.content.getSystemService
import io.ashkay.lib.R
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
    var instance: CrashGrabberComponent? = null

    private const val SHORTCUT_ID = "io.ashkay.crashgrabber"

    fun init(application: Application) {
        instance = DaggerCrashGrabberComponent.factory().build(application)

        Thread.setDefaultUncaughtExceptionHandler(CrashReporterExceptionHandler { _, throwable ->
            GlobalScope.launch {
                println("ASHTEST: inserting crash log")
                instance!!.getDao().insertCrashLogEntry(CrashLogEntity(throwable.message.orEmpty()))
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
        context.startActivity(getLaunchIntent(context))
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