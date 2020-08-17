package ru.skillbranch.sbdelivery.ui.activity.root

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.skillbranch.sbdelivery.repository.RootRepository
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState
import timber.log.Timber

class RootViewModel(
    handle: SavedStateHandle,
    private val repository: RootRepository
) : BaseViewModel<RootState>(handle, RootState()) {

    init {
        initData()
    }

    private fun initData() {
        Timber.d("Init RootViewModel Data")
        viewModelScope.launch {
            repository.synchronizeData(repository.getLastSyncDate())
            repository.setLastSyncDate()
            Timber.d("LastUpdateDate - > ${repository.getLastSyncDate()}")
        }
    }

}

class RootState : IViewModelState