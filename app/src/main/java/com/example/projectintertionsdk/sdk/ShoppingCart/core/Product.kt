package com.example.projectintertionsdk.sdk.ShoppingCart.core

/**
 * Defines the basic properties of a product.
 */
interface Product {
    val id: String
    val name: String
    val price: Double
    val imageRes: Int
    val description: String
}

/**
 * A simple data class implementation of the [Product] interface.
 */
data class SimpleProduct (
    override val id: String,
    override val name: String,
    override val price: Double,
    override val imageRes: Int,
    override val description: String
) : Product
