package io.ashkay.lib.internal.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import io.ashkay.lib.internal.data.entity.CrashLogEntity

@Database(
    entities = [CrashLogEntity::class],
    version = 1
)
abstract class CrashLogDatabase : RoomDatabase() {

    abstract val foodEntryDao: CrashLogDao

    companion object {
        const val DATABASE_NAME = "crash_grabber"
    }
}