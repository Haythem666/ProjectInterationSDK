package com.example.projectintertionsdk.sdk.ShoppingCart.views

import androidx.compose.runtime.Composable
import com.example.projectintertionsdk.sdk.ShoppingCart.core.ShoppingCartManager

/**
 * Defines the contract for displaying the shopping cart.
 */
interface CartView {
    /**
     * Displays the shopping cart UI.
     * @param cartManager The shopping cart manager.
     * @param onCheckout A callback for when the user wants to checkout.
     */
    @Composable
    fun Display(
        cartManager: ShoppingCartManager,
        onCheckout: () -> Unit
    )
}