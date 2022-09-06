package com.codenome.cstv

import android.app.Application
import com.codenome.cstv.di.repositoryModule
import com.codenome.cstv.di.retrofitModule
import com.codenome.cstv.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CSTVApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@CSTVApplication)
            modules(listOf(repositoryModule, retrofitModule, viewModelModule))
        }
    }
}