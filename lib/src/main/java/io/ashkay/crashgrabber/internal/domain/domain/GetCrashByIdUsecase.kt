package io.ashkay.crashgrabber.internal.domain.domain

import io.ashkay.crashgrabber.internal.data.entity.CrashLogEntity
import io.ashkay.crashgrabber.internal.domain.repository.CrashLogRepository
import javax.inject.Inject

internal class GetCrashByIdUsecase @Inject constructor(
    private val repository: CrashLogRepository
) {
    suspend operator fun invoke(id: Int): CrashLogEntity {
        return repository.getCrashByIdFromDb(id)
    }
}