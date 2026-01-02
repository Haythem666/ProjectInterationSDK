package com.example.projectintertionsdk.sdk.ShoppingCart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.projectintertionsdk.sdk.ShoppingCart.core.ShoppingCartManager
import com.example.projectintertionsdk.sdk.ShoppingCart.views.CartView
import com.example.projectintertionsdk.sdk.ShoppingCart.views.FullCartView

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