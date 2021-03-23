package com.ignitelabs.assessment.di.builder

import com.ignitelabs.assessment.activities.main.main.MainActivity
import com.ignitelabs.assessment.activities.main.viewresult.ViewResult
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    internal abstract fun contributeMainActivity(): MainActivity



    @ContributesAndroidInjector()
    internal abstract fun contributeViewResult(): ViewResult

}