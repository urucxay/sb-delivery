package ru.skillbranch.sbdelivery.di

import ru.skillbranch.sbdelivery.di.modules.*

val modulesList = listOf(
    networkModule,
    dbModule,
    viewModelsModule,
    repositoryModule,
    prefModule
)