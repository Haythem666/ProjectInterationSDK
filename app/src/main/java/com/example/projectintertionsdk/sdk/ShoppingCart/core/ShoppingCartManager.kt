package com.example.projectintertionsdk.sdk.ShoppingCart.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ShoppingCartManager (
    private val cartCalculator: DefaultCartCalculator
) {
    var items by mutableStateOf<List<CartItem>>(emptyList())
        private set

    val itemCount: Int
        get() = items.sumOf { it.quantity }

    val subtotal: Double
        get() = cartCalculator.calculateSubtotal(items)

    val shipping: Double
        get() = cartCalculator.calculateShipping(subtotal,itemCount)

    val total: Double
        get() = cartCalculator.calculateTotal(subtotal,shipping)

    val isEmpty: Boolean
        get() = items.isEmpty()


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

    fun removeProduct(productId:String){
        items = items.filter {it.product.id != productId}
    }


    fun incrementQuantity(productId: String){
        items = items.map {
            if (it.product.id == productId){
                it.incrementQuantity()
            } else {
                it
            }
        }
    }

    fun decrementQuantity(productId: String){
        items = items.mapNotNull {
            if (it.product.id == productId) {
                val updated = it.decrementQuantity()
                if (updated.quantity == 0) null else updated
            } else it
        }
    }


    fun clear() {
        items = emptyList()
    }




}