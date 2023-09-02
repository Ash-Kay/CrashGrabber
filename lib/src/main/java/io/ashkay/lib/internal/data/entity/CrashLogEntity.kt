package io.ashkay.lib.internal.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crashLog")
data class CrashLogEntity(
    @PrimaryKey
    @ColumnInfo(name = "msg")
    val message: String
)
