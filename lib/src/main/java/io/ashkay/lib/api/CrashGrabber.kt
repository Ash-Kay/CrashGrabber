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
import com.google.gson.Gson
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
                instance!!.getDao().insertCrashLogEntry(
                    CrashLogEntity(
                        fileName = throwable.stackTrace[0].fileName,
                        stacktrace = getStackTraceString(throwable),
                        timestamp = System.currentTimeMillis(),
                        meta = getDeviceMetaAsJson()
                    )
                )
            }
        })
    }

    private fun getDeviceMetaAsJson(): String? {
        val os = System.getProperty("os.version") // OS version
        val apiLevel = Build.VERSION.SDK_INT      // API Level
        val device = Build.DEVICE           // Device
        val model = Build.MODEL            // Model
        val product = Build.PRODUCT          // Product

        val map = HashMap<String, String>()
        map["OS"] = os.orEmpty()
        map["API_LEVEL"] = apiLevel.toString()
        map["DEVICE"] = device.orEmpty()
        map["MODEL"] = model.orEmpty()
        map["PRODUCT"] = product.orEmpty()

        return kotlin.runCatching {
            Gson().toJson(map)
        }.getOrNull()
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