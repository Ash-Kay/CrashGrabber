package io.ashkay.lib.internal.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crashLog")
data class CrashLogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "fileName")
    val fileName: String,

    @ColumnInfo(name = "stacktrace")
    val stacktrace: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long,

    @ColumnInfo(name = "meta")
    val meta: String?
)

/*
* file name
* exception message
* stacktrace
* timestamp
* meta
*   device name
*   device model
*   android version
*   etc.
*
* */