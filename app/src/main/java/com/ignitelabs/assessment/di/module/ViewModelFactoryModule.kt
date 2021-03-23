package com.ignitelabs.assessment.di.module

import androidx.lifecycle.ViewModelProvider
import com.ignitelabs.assessment.activities.main.main.MainViewModelFactory
import com.ignitelabs.assessment.activities.main.viewresult.ViewResultViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    /**
     *  This module basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  ViewModels as key,
     * and a Provider that will build a tViewModelsFactory
     * object.
     */
    @Binds
    abstract fun bindMainViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindViewResultViewModelFactory(factory: ViewResultViewModelFactory): ViewModelProvider.Factory

}