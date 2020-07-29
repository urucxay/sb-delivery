package ru.skillbranch.sbdelivery.repository

import ru.skillbranch.sbdelivery.network.DeliveryService
import ru.skillbranch.sbdelivery.network.response.DishResponse

class DeliveryRepository(
    private val service: DeliveryService
) {

    suspend fun getDishes() : List<DishResponse> = service.getDishes()

}