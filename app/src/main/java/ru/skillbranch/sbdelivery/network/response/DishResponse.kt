package ru.skillbranch.sbdelivery.network.response

import ru.skillbranch.sbdelivery.db.entitiy.Dish
import java.util.*

data class DishResponse(
    val id: String,
    val name: String,
    val description: String?,
    val image: String,
    val oldPrice: String?,
    val price: Int,
    val rating: Float,
    val likes: Int,
    val category: String,
    val commentsCount: Int,
    val active: Boolean,
    val createdAt: Long,
    val updatedAt: Long
) {
    fun convertToDish() = Dish(
        id = id,
        name = name,
        description = description,
        image = image,
        oldPrice = oldPrice,
        price = price,
        rating = rating,
        likes = likes,
        category = category,
        commentsCount = commentsCount,
        active = active,
        createdAt = Date(createdAt),
        updatedAt = Date(updatedAt)
    )
}

