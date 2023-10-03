package io.ashkay.crashgrabber.internal.data

import io.ashkay.crashgrabber.internal.data.entity.CrashLogEntity
import io.ashkay.crashgrabber.internal.data.room.CrashLogDao
import io.ashkay.crashgrabber.internal.domain.repository.CrashLogRepository


internal class CrashLogRepositoryImpl (private val crashLogDao: CrashLogDao): CrashLogRepository {
    override suspend fun getCrashByIdFromDb(id: Int): CrashLogEntity {
        return crashLogDao.getCrashById(id)
    }

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