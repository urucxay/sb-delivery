package ru.skillbranch.sbdelivery.db.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "table_favorite_info",
    foreignKeys = [ForeignKey(
        entity = Dish::class,
        parentColumns = ["id"],
        childColumns = ["dish_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FavoriteInfo(
    @PrimaryKey
    @ColumnInfo(name = "dish_id")
    val dishId: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date
)