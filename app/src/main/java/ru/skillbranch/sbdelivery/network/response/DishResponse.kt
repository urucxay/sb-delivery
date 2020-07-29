package ru.skillbranch.sbdelivery.network.response

data class DishResponse(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val oldPrice: String?,
    val price: Int,
    val rating: Double,
    val likes: Int,
    val category: String,
    val commentsCount: Int,
    val active: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)

