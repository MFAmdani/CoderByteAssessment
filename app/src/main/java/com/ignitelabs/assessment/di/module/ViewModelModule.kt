package com.ignitelabs.assessment.di.module

import androidx.lifecycle.ViewModel
import com.ignitelabs.assessment.activities.main.main.MainViewModel
import com.ignitelabs.assessment.activities.main.viewresult.ViewResultViewModel
import com.ignitelabs.assessment.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    /**
     *  This module basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  ViewModels as key,
     * and a Provider that will build a tViewModels
     * object.
     */

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewResultViewModel(viewModel: ViewResultViewModel): ViewModel

}