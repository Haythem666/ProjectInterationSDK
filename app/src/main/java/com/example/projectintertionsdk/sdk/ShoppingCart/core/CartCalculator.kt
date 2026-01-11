package com.example.projectintertionsdk.sdk.ShoppingCart.core

/**
 * Defines the contract for calculating shopping cart totals.
 */
interface CartCalculator {
    /**
     * Calculates the subtotal of all items in the cart.
     * @param items The list of items in the cart.
     * @return The calculated subtotal.
     */
    fun calculateSubtotal(items: List<CartItem>) : Double

    /**
     * Calculates the shipping cost.
     * @param subtotal The subtotal of the items in the cart.
     * @param itemCount The total number of items in the cart.
     * @return The calculated shipping cost.
     */
    fun calculateShipping(subtotal: Double, itemCount:Int): Double

    /**
     * Calculates the final total of the shopping cart.
     * @param subtotal The subtotal of the items in the cart.
     * @param shipping The shipping cost.
     * @return The final total.
     */
    fun calculateTotal(subtotal: Double,shipping: Double): Double
}

/**
 * A default implementation of the [CartCalculator].
 * @property shippingCost The fixed cost for shipping.
 */
class DefaultCartCalculator(
    private val shippingCost: Double = 5.0
): CartCalculator{
    /**
     * Calculates the subtotal by summing the subtotals of each item.
     */
    override fun calculateSubtotal(items: List<CartItem>): Double {
        return items.sumOf { it.subtotal }
    }

    /**
     * Calculates the shipping cost. Returns 0.0 if the cart is empty, otherwise returns the [shippingCost].
     */
    override fun calculateShipping(subtotal: Double, itemCount: Int): Double {
        return if (itemCount == 0) 0.0 else shippingCost
    }

    /**
     * Calculates the final total by adding shipping to the subtotal.
     */
    override fun calculateTotal(
        subtotal: Double,
        shipping: Double
    ): Double {
        return subtotal + shipping
    }

}