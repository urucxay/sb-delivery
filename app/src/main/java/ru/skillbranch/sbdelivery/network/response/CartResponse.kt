package ru.skillbranch.sbdelivery.network.response

data class CartResponse(
    val promocode: String,
    val promotext: String,
    val total: Int,
    val items: List<CartItem>
)

data class CartItem(
    val id: String,
    val amount: Int,
    val price: Int
)