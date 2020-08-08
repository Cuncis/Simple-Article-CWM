package com.cuncisboss.simplearticlecwm

import android.app.Application
import com.cuncisboss.simplearticlecwm.di.prefModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(prefModule)
        }
    }

}