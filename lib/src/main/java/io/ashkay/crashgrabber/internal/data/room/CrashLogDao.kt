package io.ashkay.crashgrabber.internal.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.ashkay.crashgrabber.internal.data.entity.CrashLogEntity

@Dao
internal interface CrashLogDao {
    @Query("SELECT * FROM crashLog WHERE id=:id")
    suspend fun getCrashById(id: Int): CrashLogEntity

    @Query("SELECT * FROM crashLog")
    suspend fun getCrashLogs(): List<CrashLogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrashLogEntry(crashLogEntity: CrashLogEntity)

    @Query("DELETE FROM crashLog")
    suspend fun clearCrashLogs()
}
