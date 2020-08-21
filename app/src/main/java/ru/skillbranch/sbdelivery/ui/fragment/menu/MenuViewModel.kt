package ru.skillbranch.sbdelivery.ui.fragment.menu

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.collect
import ru.skillbranch.sbdelivery.repository.CategoryRepository
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState

class MenuViewModel(
    handle: SavedStateHandle,
    private val categoryRepository: CategoryRepository,
) : BaseViewModel<MenuState>(handle, MenuState()) {

    val categoriesLiveData = liveData {
        categoryRepository.getCategories()
            .collect {
                emit(it.filter { item -> item.parent == null })
            }
    }

}

data class MenuState(
    val isLoading: Boolean = true
) : IViewModelState {

}