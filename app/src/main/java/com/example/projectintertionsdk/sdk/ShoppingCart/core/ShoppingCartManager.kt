package com.example.projectintertionsdk.sdk.ShoppingCart.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Manages the shopping cart, including adding, removing, and updating items.
 * It also calculates the subtotal, shipping, and total price.
 * @param cartCalculator The calculator for cart totals.
 */
class ShoppingCartManager (
    private val cartCalculator: DefaultCartCalculator
) {
    /** The list of items in the cart. */
    var items by mutableStateOf<List<CartItem>>(emptyList())
        private set

    /** The total number of items in the cart. */
    val itemCount: Int
        get() = items.sumOf { it.quantity }

    /** The subtotal of all items in the cart. */
    val subtotal: Double
        get() = cartCalculator.calculateSubtotal(items)

    /** The calculated shipping cost. */
    val shipping: Double
        get() = cartCalculator.calculateShipping(subtotal,itemCount)

    /** The final total price, including shipping. */
    val total: Double
        get() = cartCalculator.calculateTotal(subtotal,shipping)

    /** Checks if the cart is empty. */
    val isEmpty: Boolean
        get() = items.isEmpty()


    /**
     * Adds a product to the cart or increases its quantity if it already exists.
     * @param product The product to add.
     * @param quantity The quantity to add.
     */
    fun addProduct(product: Product, quantity: Int = 1){
        val existingItem = items.find { it.product.id == product.id }

        items = if (existingItem != null) {
            items.map{
                if(it.product.id == product.id) {
                    it.copy(quantity = it.quantity + quantity)
                } else {
                    it
                }
            }
        } else {
            items + CartItem(product,quantity)
        }
    }

    /**
     * Removes a product from the cart.
     * @param productId The ID of the product to remove.
     */
    fun removeProduct(productId:String){
        items = items.filter {it.product.id != productId}
    }


    /**
     * Increases the quantity of a product in the cart.
     * @param productId The ID of the product.
     */
    fun incrementQuantity(productId: String){
        items = items.map {
            if (it.product.id == productId){
                it.incrementQuantity()
            } else {
                it
            }
        }
    }

    /**
     * Decreases the quantity of a product in the cart.
     * If the quantity becomes zero, the item is removed.
     * @param productId The ID of the product.
     */
    fun decrementQuantity(productId: String){
        items = items.mapNotNull {
            if (it.product.id == productId) {
                val updated = it.decrementQuantity()
                if (updated.quantity == 0) null else updated
            } else it
        }
    }


    /** Clears all items from the cart. */
    fun clear() {
        items = emptyList()
    }




}