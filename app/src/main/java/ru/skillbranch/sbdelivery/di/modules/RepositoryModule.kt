package ru.skillbranch.sbdelivery.di.modules

import org.koin.dsl.module
import ru.skillbranch.sbdelivery.repository.DishRepository
import ru.skillbranch.sbdelivery.repository.RootRepository

val repositoryModule = module {

    single {
        DishRepository(
            service = get(),
            dishDao = get(),
            reviewDao = get(),
            prefManager = get()
        )
    }

    single {
        RootRepository(
            service = get(),
            dishDao = get(),
            prefManager = get()
        )
    }

}