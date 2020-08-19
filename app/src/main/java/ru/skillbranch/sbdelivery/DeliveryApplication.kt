package ru.skillbranch.sbdelivery

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.skillbranch.sbdelivery.di.modulesList
import timber.log.Timber

class DeliveryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initKoin()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        startKoin {
            //Temporary fix for Kotlin 1.4
            androidLogger(Level.ERROR)
            androidContext(this@DeliveryApplication)
            modules(modulesList)
        }
    }

}