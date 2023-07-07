package com.achsanit.footballstandings.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

object KoinInitializer {

    fun init(app: Application) {
        startKoin {
            androidContext(app)
            modules(
                listOf(
                    localDbModule,
                    repositoryModule,
                    viewModelModule,
                )
            )
        }
    }
}