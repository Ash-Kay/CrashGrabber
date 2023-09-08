package io.ashkay.lib.internal.utils

import android.os.Build
import com.google.gson.Gson

fun getDeviceMetaAsJson(): String? {
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

    return runCatching {
        Gson().toJson(map)
    }.getOrNull()
}
