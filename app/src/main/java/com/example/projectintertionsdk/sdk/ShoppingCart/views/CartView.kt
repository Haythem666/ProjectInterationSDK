package com.example.projectintertionsdk.sdk.ShoppingCart.views

import android.view.Display
import androidx.compose.runtime.Composable
import com.example.projectintertionsdk.sdk.ShoppingCart.core.ShoppingCartManager

interface CartView {
    @Composable
    fun Display(
        cartManager: ShoppingCartManager,
        onCheckout: () -> Unit
    )
}