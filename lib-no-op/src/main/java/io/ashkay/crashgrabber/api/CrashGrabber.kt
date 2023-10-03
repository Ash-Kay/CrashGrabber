package io.ashkay.crashgrabber.api

import android.content.Context
import android.content.Intent

object CrashGrabber {
    fun init(context: Context) {
        //no op
    }

    /**
     * Create a shortcut to launch CrashGrabber UI.
     * @param context An Android [Context].
     */
    fun createShortcut(context: Context) {
        //no op
    }

    fun clear() {
        //no-op
    }

    /**
     * Get an Intent to launch the CrashGrabber UI directly.
     * @param context An Android [Context].
     * @return An Intent for the main CrashGrabber Activity that can be started with [Context.startActivity].
     */
    @JvmStatic
    fun getLaunchIntent(context: Context): Intent {
        //no-op
        return Intent()
    }
}