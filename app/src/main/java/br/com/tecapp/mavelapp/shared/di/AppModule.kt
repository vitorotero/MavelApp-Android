package br.com.tecapp.mavelapp.shared.di

import br.com.tecapp.mavelapp.shared.utils.SharedPreferencesHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    factory { SharedPreferencesHelper(androidContext()) }
}

