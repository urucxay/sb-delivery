package ru.skillbranch.sbdelivery.network.response

data class CategoryResponse(
    val categoryId: String,
    val name: String,
    val order: Int,
    val icon: String?,
    val parent: String?,
    val active: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)