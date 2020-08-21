package ru.skillbranch.sbdelivery.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import ru.skillbranch.sbdelivery.network.response.CategoryResponse
import ru.skillbranch.sbdelivery.network.response.DishResponse
import ru.skillbranch.sbdelivery.network.response.ReviewResponse

interface DeliveryService {

    @GET("/dishes")
    suspend fun fetchDishes(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 10,
        @Header("If-Modified-Since") header: String = "Wed, 21 Oct 2015 07:28:00 GMT"
    ): List<DishResponse>

    @GET("/main/recommend")
    suspend fun fetchRecommendDishes(
        @Header("If-Modified-Since") header: String = "Wed, 21 Oct 2015 07:28:00 GMT"
    ): List<String>

    @GET("/reviews/{dishId}")
    suspend fun fetchReviews(
        @Path("dishId") dishId: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 10,
        @Header("If-Modified-Since") header: String = "Wed, 21 Oct 2015 07:28:00 GMT"
    ): List<ReviewResponse>


    @GET("/categories")
    suspend fun fetchCategories(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 40,
        @Header("If-Modified-Since") header: String = "Wed, 21 Oct 2015 07:28:00 GMT"
    ): List<CategoryResponse>

}
