package ru.skillbranch.sbdelivery.ui.fragment.dish

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
import timber.log.Timber

class DishViewModel(
    handle: SavedStateHandle,
    private val dishId: String,
    private val dishRepository: DishRepository
) : BaseViewModel<DishState>(handle, DishState()) {

    val reviewsLiveData = liveData {
        dishRepository.getReviews(
            dishId = dishId,
            onSuccess = {
                //TODO change loading status
            },
            onError = {
                //TODO show notification
            }
        ).collect { emit(it) }
    }

    init {
        viewModelScope.launch {
            val data = dishRepository.getDishById(dishId)
                .asLiveData()
            Timber.d("date -> ${data.value}")
            subscribeOnDataSource(data) { dish, currentState ->
                currentState.copy(
                    rating = dish.rating,
                    price = dish.price,
                    oldPrice = dish.oldPrice,
                    image = dish.image,
                    isFavorite = dish.isFavorite
                )
            }
        }
    }

    fun handleToggleFavorite() {
        viewModelScope.launch() {
            dishRepository.toggleFavorite(dishId)
        }
    }

    fun handleAddToCart(count: Int) {
        notify(Notification.TextMessage("was added to the cart"))
        dishRepository.addToCart(dishId, count)
    }

    fun handleAddReview() {

    }


}

data class DishState(
    val isFavorite: Boolean = false,
    val count: Int = 1,
    val rating: Float = 0F,
    val price: Int = 0,
    val oldPrice: String? = null,
    val image: String? = null
) : IViewModelState {

}