package com.task.task.di

import com.task.task.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface RetroComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)
}