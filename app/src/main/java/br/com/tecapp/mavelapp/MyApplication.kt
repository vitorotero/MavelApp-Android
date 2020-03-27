package br.com.tecapp.mavelapp

import android.app.Application
import br.com.tecapp.mavelapp.shared.di.appModule
import br.com.tecapp.mavelapp.shared.di.managerModule
import br.com.tecapp.mavelapp.shared.di.serviceModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startDi()
    }

    private fun startDi() {
        val moduleList = listOf(
            appModule,
            serviceModule,
            managerModule
        )
        startKoin(this, moduleList)
    }

}