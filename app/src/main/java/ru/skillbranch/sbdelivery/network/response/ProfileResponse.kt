package ru.skillbranch.sbdelivery.network.response

data class ProfileResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)