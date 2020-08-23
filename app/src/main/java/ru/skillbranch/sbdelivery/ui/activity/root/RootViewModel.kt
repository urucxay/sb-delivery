package ru.skillbranch.sbdelivery.ui.activity.root

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.skillbranch.sbdelivery.repository.RootRepository
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState
import ru.skillbranch.sbdelivery.ui.base.Notification
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
            repository.synchronizeData(
                ifModified = repository.getLastSyncDate(),
                onSuccess = { repository.setLastSyncDate() },
                onError = { notify(Notification.TextMessage(it)) }
            )
            Timber.d("LastUpdateDate - > ${repository.getLastSyncDate()}")
        }
    }

}

class RootState : IViewModelState