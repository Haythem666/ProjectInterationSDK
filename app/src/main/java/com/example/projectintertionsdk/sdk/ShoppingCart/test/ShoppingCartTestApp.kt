package com.example.projectintertionsdk.sdk.ShoppingCart.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.projectintertionsdk.R
import com.example.projectintertionsdk.sdk.PasswordStrengthMeter.PasswordStrengthMeter
import com.example.projectintertionsdk.sdk.ShoppingCart.ShoppingCart
import com.example.projectintertionsdk.sdk.ShoppingCart.core.DefaultCartCalculator
import com.example.projectintertionsdk.sdk.ShoppingCart.core.Product
import com.example.projectintertionsdk.sdk.ShoppingCart.core.ShoppingCartManager
import com.example.projectintertionsdk.sdk.ShoppingCart.core.SimpleProduct

/**
 * A test application to demonstrate the shopping cart functionality.
 */
@Composable
fun ShoppingCartTestApp() {

    val cartManager = remember {
        ShoppingCartManager(
            cartCalculator = DefaultCartCalculator(
                shippingCost = 5.0
            )
        )
    }

    var showCart by remember { mutableStateOf(false) }
    var isCheckingOut by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }

    MaterialTheme {

        Scaffold(
            topBar = {
                TopBar(
                    cartManager = cartManager,
                    onCartClick = { showCart = !showCart }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (showCart) {

                    if (isCheckingOut) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Complete your order", style = MaterialTheme.typography.headlineMedium)
                            Text("Please set a password for your account to continue.")

                            Spacer(modifier = Modifier.height(16.dp))


                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text("Password") },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.padding(10.dp))

                            PasswordStrengthMeter(
                                password = password
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {},
                                enabled = password.length > 6
                            ) {
                                Text("Confirm Purchase (${String.format("%.2f", cartManager.total)} €)")
                            }

                        }
                    } else {
                        ShoppingCart(
                            cartManager = cartManager,
                            onCheckout = { isCheckingOut = true }
                        )
                    }


                } else {
                    // Product catalog
                    ProductCatalog(cartManager)
                }
            }
        }


    }

}

/**
 * The top app bar for the shopping cart test app.
 * @param cartManager The shopping cart manager.
 * @param onCartClick A callback for when the cart icon is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    cartManager: ShoppingCartManager,
    onCartClick: () -> Unit
) {
    TopAppBar(
        title = { Text("Shopping Cart") },
        actions = {

            IconButton(onClick = onCartClick) {
                BadgedBox(
                    badge = {
                        if (cartManager.itemCount > 0) {
                            Badge {
                                Text(cartManager.itemCount.toString())
                            }
                        }
                    }
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                }
            }
        }
    )
}

/**
 * A catalog of products that can be added to the cart.
 * @param cartManager The shopping cart manager.
 */
@Composable
private fun ProductCatalog(cartManager: ShoppingCartManager) {
    val products = remember {
        listOf(
            SimpleProduct(
                "1", "Laptop Pro", 1299.99, description = "Powerful portable computer",
                imageRes = R.drawable.laptopproimage
            ),
            SimpleProduct(
                "2", "Wireless Mouse", 29.99, description = "Ergonomic and precise",
                imageRes = R.drawable.mouseimage
            ),
            SimpleProduct(
                "3", "Mechanical Keyboard", 149.99, description = "Cherry MX switches",
                imageRes = R.drawable.keyboardimage
            ),
            SimpleProduct(
                "4", "27\" Monitor", 399.99, description = "4K HDR Display",
                imageRes = R.drawable.monitorimage
            ),
            SimpleProduct(
                "5", "HD Webcam", 79.99, description = "1080p 60fps",
                imageRes = R.drawable.webcamimage
            ),
            SimpleProduct(
                "6", "Headphones", 199.99, description = "Active noise cancellation",
                imageRes = R.drawable.headphoneimage
            ),
            SimpleProduct(
                "7", "USB-C Hub", 49.99, description = "7 ports connectivity",
                imageRes = R.drawable.usbcimage
            ),
            SimpleProduct(
                "8", "XL Mousepad", 19.99, description = "Premium gaming surface",
                imageRes = R.drawable.mousepadimage
            ),
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // Product grid
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 180.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onAddToCart = { cartManager.addProduct(product) }
                )
            }
        }
    }
}

/**
 * A card that displays a single product.
 * @param product The product to display.
 * @param onAddToCart A callback for when the user adds the product to the cart.
 */
@Composable
private fun ProductCard(
    product: Product,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier.fillMaxWidth().height(150.dp)
            )
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            Text(
                text = "${String.format("%.2f", product.price)} €",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Button(
                onClick = onAddToCart,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null
                )

            }
        }
    }
}
