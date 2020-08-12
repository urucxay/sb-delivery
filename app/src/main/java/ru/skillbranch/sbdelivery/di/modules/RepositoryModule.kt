package ru.skillbranch.sbdelivery.di.modules

import org.koin.dsl.module
import ru.skillbranch.sbdelivery.repository.DishRepository

val repositoryModule = module {

    single {
        DishRepository(
            service = get(),
            dishDao = get(),
            reviewDao = get(),
            prefManager = get()
        )
    }

}