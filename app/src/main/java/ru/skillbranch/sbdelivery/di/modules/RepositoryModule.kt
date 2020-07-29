package ru.skillbranch.sbdelivery.di.modules

import org.koin.dsl.module
import ru.skillbranch.sbdelivery.repository.DeliveryRepository

val repositoryModule = module {

    single {
        DeliveryRepository(
            service = get()
        )
    }

}