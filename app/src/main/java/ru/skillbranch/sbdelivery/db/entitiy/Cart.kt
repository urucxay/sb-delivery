package ru.skillbranch.sbdelivery.db.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "table_cart",
    foreignKeys = [ForeignKey(
        entity = Dish::class,
        parentColumns = ["id"],
        childColumns = ["dish_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Cart(
    @PrimaryKey
    @ColumnInfo(name = "dish_id")
    val dishId: String,
    val count: Int
)