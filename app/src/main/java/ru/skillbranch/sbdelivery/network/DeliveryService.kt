package ru.skillbranch.sbdelivery.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.skillbranch.sbdelivery.network.response.DishResponse

interface DeliveryService {

    @GET("/dishes")
    suspend fun getDishes(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 10,
        @Header("If-Modified-Since") header: String = "Wed, 21 Oct 2015 07:28:00 GMT"
    ) : List<DishResponse>

}
