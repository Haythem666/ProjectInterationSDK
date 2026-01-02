package com.example.projectintertionsdk.sdk.ShoppingCart.core

data class CartItem(
    val product: Product,
    val quantity: Int = 1
){
    val subtotal: Double
        get() = product.price * quantity
    fun incrementQuantity(): CartItem= copy(quantity = quantity + 1)
    fun decrementQuantity(): CartItem= copy(quantity = maxOf(1, quantity - 1))
}


