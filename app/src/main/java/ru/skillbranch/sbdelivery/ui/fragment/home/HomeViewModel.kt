package ru.skillbranch.sbdelivery.ui.fragment.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.skillbranch.sbdelivery.repository.DishRepository
import ru.skillbranch.sbdelivery.ui.base.BaseViewModel
import ru.skillbranch.sbdelivery.ui.base.IViewModelState
import ru.skillbranch.sbdelivery.ui.base.Notification

class HomeViewModel(
    handle: SavedStateHandle,
    private val dishRepository: DishRepository
) : BaseViewModel<HomeState>(handle, HomeState()) {

    val recommendDishes = liveData {
        dishRepository.getRecommendDishes().collect { emit(it) }
    }
    val bestDishes = dishRepository.getDishesByRating(rating = 1F).asLiveData()
    val popularDishes = dishRepository.getDishesWithMaxLikeCount().asLiveData()

    fun handleToggleFavorite(dishId: String) {
        viewModelScope.launch() {
            dishRepository.toggleFavorite(dishId)
        }
    }

    fun handleAddToCart(id: String, count: Int = 1) {
        notify(Notification.TextMessage("was added to the cart"))
        dishRepository.addToCart(id, count)
    }

}

class HomeState : IViewModelState