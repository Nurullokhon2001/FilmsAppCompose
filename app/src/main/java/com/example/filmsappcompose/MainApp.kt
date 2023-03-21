package com.example.filmsappcompose

import android.app.Application
import com.example.filmsappcompose.di.AppComponent
import com.example.filmsappcompose.di.DaggerAppComponent
import com.example.filmsappcompose.di.DataModule

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().dataModule(DataModule(this)).build()
    }
}