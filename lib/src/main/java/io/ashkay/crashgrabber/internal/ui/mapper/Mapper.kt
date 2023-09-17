package io.ashkay.crashgrabber.internal.ui.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ashkay.crashgrabber.internal.data.entity.CrashLogEntity
import io.ashkay.crashgrabber.internal.ui.model.DetailScreenModel
import io.ashkay.crashgrabber.internal.ui.model.MainScreenModel
import java.lang.reflect.Type


fun CrashLogEntity.toMainScreenModel(): MainScreenModel {
    return MainScreenModel(
        id = this.id,
        fileName = this.fileName,
        message = this.message,
        timestamp = this.timestamp
    )
}

fun CrashLogEntity.toDetailScreenModel(): DetailScreenModel {

    val metaMap = kotlin.runCatching {
        val type: Type = object : TypeToken<Map<String, String>>() {}.type
        Gson().fromJson<Map<String, String>>(this.meta, type)
    }.getOrNull()

    return DetailScreenModel(
        id = this.id,
        fileName = this.fileName,
        stacktrace = this.stacktrace,
        timestamp = this.timestamp,
        meta = metaMap.orEmpty()
    )
}