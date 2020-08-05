package ru.skillbranch.sbdelivery.ui.fragment.dish

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.liveData
import ru.skillbranch.sbdelivery.repository.DeliveryRepository
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState
import timber.log.Timber

class DishViewModel(
    handle: SavedStateHandle,
    dishId: String,
    private val repository: DeliveryRepository
) : BaseViewModel<DishState>(handle, DishState()) {

    val reviews = liveData {
        val list = repository.getReviews(dishId)
        Timber.d("list -> $list")
        emit(list)
    }

}

class DishState : IViewModelState