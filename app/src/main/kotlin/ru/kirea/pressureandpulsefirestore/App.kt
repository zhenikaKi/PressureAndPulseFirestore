package ru.kirea.pressureandpulsefirestore

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.kirea.pressureandpulsefirestore.di.Modules

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                Modules.application,
                Modules.mainWindow,
            )
        }
    }
}