package ru.skillbranch.sbdelivery.repository

import ru.skillbranch.sbdelivery.network.DeliveryService
import ru.skillbranch.sbdelivery.network.response.DishResponse
import ru.skillbranch.sbdelivery.network.response.ReviewResponse

class DeliveryRepository(
    private val service: DeliveryService
) {
    suspend fun getDishes() : List<DishResponse> = service.getDishes()

    suspend fun getReviews(dishId: String) : List<ReviewResponse> = service.getReviews(dishId)

}