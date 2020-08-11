package ru.skillbranch.sbdelivery.di.modules

import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.skillbranch.sbdelivery.ui.activity.root.RootViewModel
import ru.skillbranch.sbdelivery.ui.fragment.dish.DishViewModel
import ru.skillbranch.sbdelivery.ui.fragment.home.HomeViewModel
import ru.skillbranch.sbdelivery.ui.fragment.menu.MenuViewModel
import ru.skillbranch.sbdelivery.ui.search.SearchViewModel

val viewModelsModule = module {

    viewModel { (handle: SavedStateHandle) -> RootViewModel(
        handle = handle,
        repository = get()
    ) }

    viewModel { (handle: SavedStateHandle) -> HomeViewModel(
        handle = handle,
        repository = get()
    ) }

    viewModel { (handle: SavedStateHandle, dishId: String) -> DishViewModel(
        handle = handle,
        dishId = dishId,
        dishRepository = get()
    ) }

    viewModel { (handle: SavedStateHandle) -> MenuViewModel(
        handle = handle
    ) }

}