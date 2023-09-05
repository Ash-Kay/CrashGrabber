package io.ashkay.lib.internal.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import io.ashkay.lib.internal.ui.CrashDetailsViewModel
import io.ashkay.lib.internal.ui.CrashGrabberMainViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ClassKey(CrashGrabberMainViewModel::class)
    abstract fun providesMainViewModel(viewModel: CrashGrabberMainViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(CrashDetailsViewModel::class)
    abstract fun providesCrashDetailsViewModel(viewModel: CrashDetailsViewModel): ViewModel
}