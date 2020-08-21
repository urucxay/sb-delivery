package ru.skillbranch.sbdelivery.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.skillbranch.sbdelivery.network.DeliveryService
import ru.skillbranch.sbdelivery.network.response.CategoryResponse

class CategoryRepository(
    private val service: DeliveryService,
) {

    suspend fun getCategories(): Flow<List<CategoryResponse>> = flow {
        val categories = service.fetchCategories()
        emit(categories)
    }

}