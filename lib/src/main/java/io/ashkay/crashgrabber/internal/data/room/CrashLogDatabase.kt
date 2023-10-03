package io.ashkay.crashgrabber.internal.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import io.ashkay.crashgrabber.internal.data.entity.CrashLogEntity

@Database(
    entities = [CrashLogEntity::class],
    version = 1
)
internal abstract class CrashLogDatabase : RoomDatabase() {

    abstract val crashLogDao: CrashLogDao

    companion object {
        const val DATABASE_NAME = "crash_grabber"
    }
}