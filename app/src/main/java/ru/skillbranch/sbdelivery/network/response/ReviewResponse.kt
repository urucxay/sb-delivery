package ru.skillbranch.sbdelivery.network.response

data class ReviewResponse(
    val dishId: String,
    val author: String,
    val date: String,
    val order: Int,
    val rating: Double,
    val text: String,
    val active: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)
