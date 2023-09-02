package io.ashkay.lib.internal.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import io.ashkay.lib.internal.data.room.CrashLogDao
import io.ashkay.lib.internal.ui.CrashGrabberMainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, DataModule::class])
interface CrashGrabberComponent {

    fun inject(crashGrabber: CrashGrabberMainActivity)

    fun getDao(): CrashLogDao

    @Component.Factory
    interface Factory {
        fun build(@BindsInstance application: Application): CrashGrabberComponent
    }
}
