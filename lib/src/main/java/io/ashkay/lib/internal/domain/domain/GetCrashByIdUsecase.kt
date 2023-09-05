package io.ashkay.lib.internal.domain.domain

import io.ashkay.lib.internal.data.entity.CrashLogEntity
import io.ashkay.lib.internal.domain.repository.CrashLogRepository
import javax.inject.Inject

class GetCrashByIdUsecase @Inject constructor(
    private val repository: CrashLogRepository
) {
    suspend operator fun invoke(id: Int): CrashLogEntity {
        return repository.getCrashByIdFromDb(id)
    }
}