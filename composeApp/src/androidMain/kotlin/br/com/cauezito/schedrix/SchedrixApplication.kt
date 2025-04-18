package br.com.cauezito.schedrix

import android.app.Application
import org.koin.core.context.startKoin

class SchedrixApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // TODO declare modules
        }
    }
}