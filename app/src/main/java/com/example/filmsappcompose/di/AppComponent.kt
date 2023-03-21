package com.example.filmsappcompose.di

import com.example.filmsappcompose.MainActivity
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}