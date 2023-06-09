package com.example.filmsappcompose

import android.app.Application
import com.example.filmsappcompose.di.AppComponent
import com.example.filmsappcompose.di.DaggerAppComponent
import timber.log.Timber


class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(this).build()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}