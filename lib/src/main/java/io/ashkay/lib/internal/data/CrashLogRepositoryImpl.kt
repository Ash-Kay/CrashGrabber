package io.ashkay.lib.internal.data

import io.ashkay.lib.internal.data.entity.CrashLogEntity
import io.ashkay.lib.internal.data.room.CrashLogDao
import io.ashkay.lib.internal.domain.repository.CrashLogRepository

class CrashLogRepositoryImpl (private val crashLogDao: CrashLogDao): CrashLogRepository {
    override suspend fun getCrashLogsFromDb(): List<CrashLogEntity> {
        return crashLogDao.getCrashLogs()
    }

    override suspend fun addCrashLogIntoDb(crashLogEntity: CrashLogEntity) {
        crashLogDao.insertCrashLogEntry(crashLogEntity)
    }

    override suspend fun clearCrashLogDb() {
        crashLogDao.clearCrashLogs()
    }
}