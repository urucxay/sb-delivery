package ru.skillbranch.sbdelivery.di.modules

import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.skillbranch.sbdelivery.ui.activity.root.RootViewModel
import ru.skillbranch.sbdelivery.ui.fragment.dish.DishViewModel
import ru.skillbranch.sbdelivery.ui.fragment.home.HomeViewModel
import ru.skillbranch.sbdelivery.ui.fragment.login.LoginViewModel
import ru.skillbranch.sbdelivery.ui.fragment.menu.MenuViewModel
import ru.skillbranch.sbdelivery.ui.fragment.register.RegisterViewModel
import ru.skillbranch.sbdelivery.ui.fragment.restore.RestoreViewModel
import ru.skillbranch.sbdelivery.ui.search.SearchViewModel

val viewModelsModule = module {

    viewModel { (handle: SavedStateHandle) -> RootViewModel(
        handle = handle,
        repository = get()
    ) }

    viewModel { (handle: SavedStateHandle) -> HomeViewModel(
        handle = handle,
        dishRepository = get()
    ) }

    viewModel { (handle: SavedStateHandle, dishId: String) -> DishViewModel(
        handle = handle,
        dishId = dishId,
        dishRepository = get()
    ) }

    viewModel { (handle: SavedStateHandle) ->
        MenuViewModel(
            handle = handle,
            categoryRepository = get()
        )
    }

    viewModel { (handle: SavedStateHandle) ->
        SearchViewModel(
            handle = handle
        )
    }

    viewModel { (handle: SavedStateHandle) ->
        LoginViewModel(
            handle = handle
        )
    }

    viewModel { (handle: SavedStateHandle) ->
        RegisterViewModel(
            handle = handle
        )
    }

    viewModel { (handle: SavedStateHandle) ->
        RestoreViewModel(
            handle = handle
        )
    }


}