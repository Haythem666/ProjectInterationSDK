package com.example.projectintertionsdk.sdk.ShoppingCart.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.example.projectintertionsdk.sdk.ShoppingCart.core.CartItem
import com.example.projectintertionsdk.sdk.ShoppingCart.core.ShoppingCartManager

/**
 * A full-screen view of the shopping cart.
 */
class FullCartView: CartView {

    /**
     * Displays the full shopping cart UI.
     * @param cartManager The shopping cart manager.
     * @param onCheckout A callback for when the user wants to checkout.
     */
    @Composable
    override fun Display(
        cartManager: ShoppingCartManager,
        onCheckout: () -> Unit
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            CartHeader(cartManager)

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(cartManager.items) { item ->
                    CartItemRow(
                        item = item,
                        onIncrement = { cartManager.incrementQuantity(item.product.id) },
                        onDecrement = { cartManager.decrementQuantity(item.product.id) },
                        onRemove = { cartManager.removeProduct(item.product.id) }

                    )
                }

            }

            CartSummary(cartManager, onCheckout)
        }
    }

    /**
     * The header for the shopping cart.
     * @param cartManager The shopping cart manager.
     */
    @Composable
    private fun CartHeader(cartManager: ShoppingCartManager){
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )

                Text(
                    text = "My Basket",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = Bold
                )
            }

            if (!cartManager.isEmpty) {
                TextButton(onClick = { cartManager.clear() }) {
                    Icon(Icons.Default.Delete , contentDescription = null)
                    Spacer(Modifier.width(4.dp))
                    Text("Empty")
                }
            }


        }
    }

    /**
     * A row that displays a single cart item.
     * @param item The cart item to display.
     * @param onIncrement A callback to increment the item quantity.
     * @param onDecrement A callback to decrement the item quantity.
     * @param onRemove A callback to remove the item from the cart.
     */
    @Composable
    private fun CartItemRow(item: CartItem, onIncrement: () -> Unit, onDecrement: () -> Unit, onRemove: () -> Unit){
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {


                    Text(
                        text = item.product.name,
                        style = MaterialTheme.typography.titleMedium,
                    )

                    item.product.imageRes

                    Text(
                        text = item.product.description,
                        style = MaterialTheme.typography.bodySmall,
                    )

                    Text(
                        text = "${String.format("%.2f", item.product.price)} € x ${item.quantity}",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    //Total price
                    Text(
                        text= "${String.format("%.2f", item.subtotal)} €",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = Bold
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onDecrement,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Decrement")
                        }

                        Text(
                            text = item.quantity.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = Bold
                        )

                        IconButton(
                            onClick = onIncrement,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Increment")
                        }

                        IconButton(
                            onClick = onRemove,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Remove",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }


                    }


                }

            }

        }
    }

    /**
     * The summary section of the shopping cart.
     * @param cartManager The shopping cart manager.
     * @param onCheckout A callback for when the user wants to checkout.
     */
    @Composable
    private fun CartSummary(cartManager: ShoppingCartManager, onCheckout: () -> Unit){

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            SummaryRow("Subtotal",cartManager.subtotal)

            if(cartManager.shipping > 0){
                SummaryRow("Shipping", cartManager.shipping)
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            //Total
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = Bold
                )
                Text(
                    text = "${String.format("%.2f", cartManager.total)} €",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onCheckout,
                modifier = Modifier.fillMaxWidth(),
                enabled = !cartManager.isEmpty,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF006837),
                    contentColor = Color.White
                )
            ) {
                Text("Check out", style = MaterialTheme.typography.titleMedium)
            }

        }
    }

    /**
     * A row that displays a summary line item.
     * @param label The label for the line item.
     * @param value The value of the line item.
     * @param note An optional note to display instead of the value.
     */
    @Composable
    private fun SummaryRow(label: String, value: Double, note: String? = null){

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            if(note!=null){
                Text(text = note,
                    style = MaterialTheme.typography.bodyLarge,
                    //color
                )
            } else {
                Text(
                    text = "${String.format("%.2f", value)} €",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

        }
    }

}


