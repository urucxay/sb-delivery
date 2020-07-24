package ru.skillbranch.sbdelivery.di.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.skillbranch.sbdelivery.ui.main.MainViewModel

val viewModelsModule = module {

    viewModel {
        MainViewModel()
    }

}