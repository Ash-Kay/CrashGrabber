package io.ashkay.lib.internal.domain.repository

import io.ashkay.lib.internal.data.entity.CrashLogEntity

interface CrashLogRepository {
    suspend fun getCrashLogsFromDb(): List<CrashLogEntity>
    suspend fun addCrashLogIntoDb(crashLogEntity: CrashLogEntity)
    suspend fun clearCrashLogDb()
}