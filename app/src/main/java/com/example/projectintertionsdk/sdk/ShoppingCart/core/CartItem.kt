package com.example.projectintertionsdk.sdk.ShoppingCart.core

/**
 * Represents an item in the shopping cart.
 * @param product The product details.
 * @param quantity The number of units of the product.
 */
data class CartItem(
    val product: Product,
    val quantity: Int = 1
){
    /** The total price for this cart item. */
    val subtotal: Double
        get() = product.price * quantity

    /** Returns a new CartItem with increased quantity. */
    fun incrementQuantity(): CartItem= copy(quantity = quantity + 1)

    /** Returns a new CartItem with decreased quantity, with a minimum of 1. */
    fun decrementQuantity(): CartItem= copy(quantity = maxOf(1, quantity - 1))
}