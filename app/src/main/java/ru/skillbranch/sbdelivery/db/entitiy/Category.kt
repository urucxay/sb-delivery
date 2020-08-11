package ru.skillbranch.sbdelivery.db.entitiy

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_category")
data class Category(
    @PrimaryKey
    @ColumnInfo(name = "category_id")
    val categoryId: String,
    val name: String,
    val order: Int,
    val icon: String?,
    @Embedded(prefix = "parent_")
    val parent: Category?,
    val active: Boolean,
    @ColumnInfo(name = "created_at")
    val createdAt: Date,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date
)