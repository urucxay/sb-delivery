package ru.skillbranch.sbdelivery.di.modules

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.skillbranch.sbdelivery.db.DeliveryDB

val dbModule = module {

    single { DeliveryDB.create(androidContext()) }

    single { get<DeliveryDB>().dishDao()}
    single { get<DeliveryDB>().reviewDao()}
}