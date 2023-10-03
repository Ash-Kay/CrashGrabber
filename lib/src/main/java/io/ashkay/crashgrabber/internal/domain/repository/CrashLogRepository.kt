package io.ashkay.crashgrabber.internal.domain.repository

import io.ashkay.crashgrabber.internal.data.entity.CrashLogEntity

internal interface CrashLogRepository {
    suspend fun getCrashByIdFromDb(id: Int): CrashLogEntity
    suspend fun getCrashLogsFromDb(): List<CrashLogEntity>
    suspend fun addCrashLogIntoDb(crashLogEntity: CrashLogEntity)
    suspend fun clearCrashLogDb()
}