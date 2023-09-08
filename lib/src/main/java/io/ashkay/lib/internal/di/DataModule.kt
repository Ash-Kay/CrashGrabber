package io.ashkay.lib.internal.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.ashkay.lib.internal.data.CrashLogRepositoryImpl
import io.ashkay.lib.internal.data.room.CrashLogDao
import io.ashkay.lib.internal.data.room.CrashLogDatabase
import io.ashkay.lib.internal.domain.repository.CrashLogRepository
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(context: Context): CrashLogDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CrashLogDatabase::class.java,
            CrashLogDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCrashLogDao(db: CrashLogDatabase): CrashLogDao {
        return db.crashLogDao
    }


    @Provides
    @Singleton
    fun provideCrashLogRepository(crashLogDao: CrashLogDao): CrashLogRepository {
        return CrashLogRepositoryImpl(crashLogDao)
    }
}