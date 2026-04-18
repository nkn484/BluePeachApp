package com.bluepeach.app.feature.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bluepeach.app.core.common.SampleStorefrontData
import com.bluepeach.app.core.ui.BluePeachColors
import com.bluepeach.app.core.ui.components.BluePeachEmptyState
import com.bluepeach.app.core.ui.components.BluePeachInfoRow
import com.bluepeach.app.core.ui.components.BluePeachPrimaryButton
import com.bluepeach.app.core.ui.components.BluePeachSecondaryButton
import com.bluepeach.app.core.ui.components.BluePeachTopBar
import com.bluepeach.app.data.model.CartLine

@Composable
fun CartScreen(onBack: () -> Unit) {
    val cartLines = remember {
        mutableStateListOf<CartLine>().also { it.addAll(SampleStorefrontData.initialCart) }
    }

    val subtotal = cartLines.sumOf { it.product.priceCents * it.quantity }
    val vat = (subtotal * 0.1).toInt()
    val shipping = if (cartLines.isEmpty()) 0 else 30000
    val total = subtotal + vat + shipping

    Scaffold(
        topBar = { BluePeachTopBar(title = "Cart", onBack = onBack) },
        containerColor = BluePeachColors.surfacePlain
    ) { innerPadding ->
        if (cartLines.isEmpty()) {
            BluePeachEmptyState(
                title = "Your cart is empty",
                message = "Add products from the storefront to start checkout.",
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            )
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(BluePeachColors.surfacePlain)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(cartLines, key = { it.product.id }) { line ->
                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        color = BluePeachColors.surfaceCard,
                        border = androidx.compose.foundation.BorderStroke(1.dp, BluePeachColors.borderSoft)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = line.product.primaryImageUrl,
                                contentDescription = line.product.name,
                                modifier = Modifier.size(78.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = line.product.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = BluePeachColors.textPrimary
                                )
                                Text(
                                    text = formatVnd(line.product.priceCents),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = BluePeachColors.textSecondary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(
                                        onClick = {
                                            val index = cartLines.indexOfFirst { it.product.id == line.product.id }
                                            if (index >= 0) {
                                                val nextQty = (cartLines[index].quantity - 1).coerceAtLeast(1)
                                                cartLines[index] = cartLines[index].copy(quantity = nextQty)
                                            }
                                        }
                                    ) {
                                        Icon(Icons.Rounded.Remove, contentDescription = "Decrease")
                                    }
                                    Text(
                                        text = "${line.quantity}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    IconButton(
                                        onClick = {
                                            val index = cartLines.indexOfFirst { it.product.id == line.product.id }
                                            if (index >= 0) {
                                                cartLines[index] = cartLines[index].copy(quantity = cartLines[index].quantity + 1)
                                            }
                                        }
                                    ) {
                                        Icon(Icons.Rounded.Add, contentDescription = "Increase")
                                    }
                                }
                            }
                            IconButton(
                                onClick = { cartLines.removeAll { it.product.id == line.product.id } }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.DeleteOutline,
                                    contentDescription = "Remove",
                                    tint = BluePeachColors.danger
                                )
                            }
                        }
                    }
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = BluePeachColors.surfaceCard,
                shape = MaterialTheme.shapes.large,
                border = androidx.compose.foundation.BorderStroke(1.dp, BluePeachColors.borderSoft)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Order summary",
                        style = MaterialTheme.typography.titleLarge,
                        color = BluePeachColors.textPrimary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    BluePeachInfoRow("Subtotal", formatVnd(subtotal))
                    BluePeachInfoRow("VAT (10%)", formatVnd(vat))
                    BluePeachInfoRow("Shipping", formatVnd(shipping))
                    BluePeachInfoRow("Total", formatVnd(total))
                    Spacer(modifier = Modifier.height(12.dp))
                    BluePeachPrimaryButton(
                        text = "Proceed to checkout",
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BluePeachSecondaryButton(
                        text = "Continue shopping",
                        onClick = onBack,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

private fun formatVnd(value: Int): String = value.toString()
    .reversed()
    .chunked(3)
    .joinToString(".")
    .reversed() + " VND"

