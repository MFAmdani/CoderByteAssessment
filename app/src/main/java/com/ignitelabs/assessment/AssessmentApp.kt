package com.ignitelabs.assessment

import android.annotation.SuppressLint
import com.ignitelabs.assessment.di.component.AppComponent
import com.ignitelabs.assessment.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

@SuppressLint("Registered")
/**
 * Kotlin    Application.
 * It's necessary for dagger injection
 */
class AssessmentApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        /**
         * Build app component
         */
        val appComponent: AppComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        /**inject application instance**/
        appComponent.inject(this)
        return appComponent
    }

    companion object {
        private var sInstance: AssessmentApp? = null
        fun getAppContext(): AssessmentApp? {
            return sInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this

    }

}