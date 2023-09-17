package io.ashkay.crashgrabber.internal.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.ashkay.crashgrabber.internal.data.CrashLogRepositoryImpl
import io.ashkay.crashgrabber.internal.data.room.CrashLogDao
import io.ashkay.crashgrabber.internal.data.room.CrashLogDatabase
import io.ashkay.crashgrabber.internal.domain.repository.CrashLogRepository
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