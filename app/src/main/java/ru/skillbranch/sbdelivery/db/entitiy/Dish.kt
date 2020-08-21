package ru.skillbranch.sbdelivery.db.entitiy

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_dish")
data class Dish(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String?,
    val image: String,
    @ColumnInfo(name = "old_price")
    val oldPrice: String?,
    val price: Int,
    val rating: Float,
    val likes: Int,
    val category: String,
    @ColumnInfo(name = "comments_count")
    val commentsCount: Int,
    val active: Boolean,
    @ColumnInfo(name = "created_at")
    val createdAt: Date,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date
)

@DatabaseView(
    """
        SELECT id, name, price, image, description, old_price, is_favorite, rating, likes
        FROM table_dish
        LEFT JOIN table_favorite_info ON id = dish_id
    """
)
data class DishItem(
    val id: String,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    @ColumnInfo(name = "old_price")
    val oldPrice: String?,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,
    val rating: Float,
    val likes: Int
)