package ru.skillbranch.sbdelivery.di.modules

import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.skillbranch.sbdelivery.ui.activity.root.RootViewModel
import ru.skillbranch.sbdelivery.ui.fragment.dish.DishViewModel
import ru.skillbranch.sbdelivery.ui.fragment.home.HomeViewModel

val viewModelsModule = module {

    viewModel { (handle: SavedStateHandle) -> RootViewModel(handle) }
    viewModel { (handle: SavedStateHandle) -> HomeViewModel(handle, get()) }
    viewModel { (handle: SavedStateHandle, dishId: String) -> DishViewModel(handle, dishId, get()) }

}