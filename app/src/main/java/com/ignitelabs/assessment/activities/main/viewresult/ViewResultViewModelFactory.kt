package com.ignitelabs.assessment.activities.main.viewresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewResultViewModelFactory @Inject constructor(private val viewModelProvider: Provider<ViewResultViewModel>) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return viewModelProvider.get() as T
    }
}