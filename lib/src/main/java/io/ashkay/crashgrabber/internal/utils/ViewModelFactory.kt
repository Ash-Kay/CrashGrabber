package io.ashkay.crashgrabber.internal.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

internal class ViewModelFactory @Inject constructor(
    private val map: Map<Class<*>, @JvmSuppressWildcards ViewModel>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (map[modelClass] == null) {
            throw UninitializedPropertyAccessException("ViewModel ${modelClass} not registered in Dagger Module")
        }

        @Suppress("UNCHECKED_CAST")
        return map[modelClass] as T
    }
}