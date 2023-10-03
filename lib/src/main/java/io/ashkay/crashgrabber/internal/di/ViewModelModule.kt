package io.ashkay.crashgrabber.internal.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import io.ashkay.crashgrabber.internal.ui.screen.detail.CrashDetailsViewModel
import io.ashkay.crashgrabber.internal.ui.screen.main.CrashGrabberMainViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ClassKey(CrashGrabberMainViewModel::class)
    abstract fun providesMainViewModel(viewModel: CrashGrabberMainViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(CrashDetailsViewModel::class)
    abstract fun providesCrashDetailsViewModel(viewModel: CrashDetailsViewModel): ViewModel
}