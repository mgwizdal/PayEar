package com.example.payear

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin

class PayEarApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, dbModule))
    }
}