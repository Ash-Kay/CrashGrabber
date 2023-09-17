package io.ashkay.crashgrabber.internal.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.ashkay.crashgrabber.internal.data.room.CrashLogDao
import io.ashkay.crashgrabber.internal.ui.screen.detail.CrashDetailsActivity
import io.ashkay.crashgrabber.internal.ui.screen.main.CrashGrabberMainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, DataModule::class])
interface CrashGrabberComponent {

    fun inject(crashGrabber: CrashGrabberMainActivity)
    fun inject(crashDetailsActivity: CrashDetailsActivity)

    fun getDao(): CrashLogDao

    @Component.Factory
    interface Factory {
        fun build(@BindsInstance context: Context): CrashGrabberComponent
    }
}
