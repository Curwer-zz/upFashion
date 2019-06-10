package com.hjalmarsson.fashion.model

data class HeaderItem (
    val productName: String = "",
    val productPrice: Long = 0L,
    val productImage: String = "",
    val displayProperties: DisplayProperties? = null
)