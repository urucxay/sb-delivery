package ru.skillbranch.sbdelivery.db.entitiy

import androidx.room.ColumnInfo

data class Review(
    @ColumnInfo(name = "dish_id")
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