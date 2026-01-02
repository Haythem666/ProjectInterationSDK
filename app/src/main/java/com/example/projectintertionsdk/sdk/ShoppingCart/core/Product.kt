package com.example.projectintertionsdk.sdk.ShoppingCart.core

interface Product {
    val id: String
    val name: String
    val price: Double
    val imageRes: Int
    val description: String
}

data class SimpleProduct (
    override val id: String,
    override val name: String,
    override val price: Double,
    override val imageRes: Int,
    override val description: String
) : Product
