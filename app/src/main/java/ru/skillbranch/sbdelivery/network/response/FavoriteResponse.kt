package ru.skillbranch.sbdelivery.network.response

data class FavoriteResponse(
    val dishId: String,
    val favorite: Boolean,
    val updatedAt: Long
)