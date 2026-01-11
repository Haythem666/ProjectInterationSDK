package com.example.projectintertionsdk.sdk.ShoppingCart

import androidx.compose.runtime.Composable
import com.example.projectintertionsdk.sdk.ShoppingCart.core.ShoppingCartManager
import com.example.projectintertionsdk.sdk.ShoppingCart.views.CartView
import com.example.projectintertionsdk.sdk.ShoppingCart.views.FullCartView

/**
 * A composable function that displays the shopping cart.
 * @param cartManager The manager for the shopping cart.
 * @param view The view used to display the cart.
 * @param onCheckout A callback for when the user wants to checkout.
 */
@Composable
fun ShoppingCart(
    cartManager: ShoppingCartManager,
    view: CartView= FullCartView(),
    onCheckout: () -> Unit = {}
) {
    view.Display(
        cartManager = cartManager,
        onCheckout = onCheckout
    )
}