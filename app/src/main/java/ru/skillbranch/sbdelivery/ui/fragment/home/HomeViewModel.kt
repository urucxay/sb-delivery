package ru.skillbranch.sbdelivery.ui.fragment.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import ru.skillbranch.sbdelivery.repository.DeliveryRepository
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState
import timber.log.Timber

class HomeViewModel(
    handle: SavedStateHandle,
    private val repository: DeliveryRepository
) : BaseViewModel<HomeState>(handle, HomeState()) {

    val dishes = liveData {
        val list = repository.getDishes()
        Timber.d("list -> $list")
        emit(list)
    }

}

class HomeState : IViewModelState