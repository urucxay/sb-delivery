package ru.skillbranch.sbdelivery.ui.search

import androidx.lifecycle.SavedStateHandle
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState

class SearchViewModel(handle: SavedStateHandle) :
    BaseViewModel<SearchState>(handle, SearchState()) {

    fun handleSearch(query: String?) {

    }

}

data class SearchState(
    val searchQuery: String? = null,
    val isLoading: Boolean = true
) : IViewModelState {

    override fun save(outState: SavedStateHandle) {
        outState.set("searchQuery", searchQuery)
    }

    override fun restore(savedState: SavedStateHandle): IViewModelState {
        return copy(
            searchQuery = savedState["searchQuery"]
        )
    }
}