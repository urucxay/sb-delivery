package ru.skillbranch.sbdelivery.di.modules

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.skillbranch.sbdelivery.pref.PrefManager

val prefModule = module {

    single {
        PrefManager(androidContext())
    }

}