package io.ashkay.lib.internal.data.entity

import androidx.room.Entity

@Entity(tableName = "crashLog")
data class CrashLogEntity(val message: String)
