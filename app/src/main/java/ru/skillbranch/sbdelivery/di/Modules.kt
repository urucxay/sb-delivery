package ru.skillbranch.sbdelivery.di

import ru.skillbranch.sbdelivery.di.modules.networkModule
import ru.skillbranch.sbdelivery.di.modules.repositoryModule
import ru.skillbranch.sbdelivery.di.modules.viewModelsModule

val modulesList = listOf(
    networkModule,
    viewModelsModule,
    repositoryModule
)