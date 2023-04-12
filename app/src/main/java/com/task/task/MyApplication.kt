package com.task.task

import android.app.Application
import com.task.task.di.DaggerRetroComponent
import com.task.task.di.RetroComponent
import com.task.task.di.RetroModule

class MyApplication : Application() {
    private lateinit var retroComponent: RetroComponent

    override fun onCreate() {
        super.onCreate()
        retroComponent = DaggerRetroComponent.builder()
            .retroModule(RetroModule())
            .build()
    }

    fun getRetoComponent(): RetroComponent {
        return retroComponent
    }
}