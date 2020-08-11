package ru.skillbranch.sbdelivery.ui.fragment.menu

import androidx.lifecycle.SavedStateHandle
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState

class MenuViewModel(handle: SavedStateHandle) : BaseViewModel<MenuState>(handle, MenuState()) {

}

data class MenuState(
    val isSearch: Boolean = false,
    val searchQuery: String? = null,
    val isLoading: Boolean = true
) : IViewModelState {

    override fun save(outState: SavedStateHandle) {
        outState.set("isSearch", isSearch)
        outState.set("searchQuery", searchQuery)
    }

    override fun restore(savedState: SavedStateHandle): IViewModelState {
        return copy(
            isSearch = savedState["isSearch"] ?: false,
            searchQuery = savedState["searchQuery"]
        )
    }
}