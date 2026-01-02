package com.example.projectintertionsdk.sdk.ShoppingCart.core

interface CartCalculator {
    fun calculateSubtotal(items: List<CartItem>) : Double
    fun calculateShipping(subtotal: Double, itemCount:Int): Double
    fun calculateTotal(subtotal: Double,shipping: Double): Double
}

class DefaultCartCalculator(
    private val shippingCost: Double = 5.0
): CartCalculator{
    override fun calculateSubtotal(items: List<CartItem>): Double {
        return items.sumOf { it.subtotal }
    }

    override fun calculateShipping(subtotal: Double, itemCount: Int): Double {
        return if (itemCount == 0) 0.0 else shippingCost
    }

    override fun calculateTotal(
        subtotal: Double,
        shipping: Double
    ): Double {
        return subtotal + shipping
    }

}