package com.bluepeach.app.feature.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bluepeach.app.core.common.SampleStorefrontData
import com.bluepeach.app.core.ui.BluePeachColors
import com.bluepeach.app.core.ui.components.BluePeachEmptyState
import com.bluepeach.app.core.ui.components.BluePeachInfoBadge
import com.bluepeach.app.core.ui.components.BluePeachInfoRow
import com.bluepeach.app.core.ui.components.BluePeachPriceBlock
import com.bluepeach.app.core.ui.components.BluePeachPrimaryButton
import com.bluepeach.app.core.ui.components.BluePeachProductCard
import com.bluepeach.app.core.ui.components.BluePeachRatingSummary
import com.bluepeach.app.core.ui.components.BluePeachSecondaryButton
import com.bluepeach.app.core.ui.components.BluePeachTagBadge
import com.bluepeach.app.core.ui.components.BluePeachTopBar

@Composable
fun ProductDetailScreen(
    productId: String,
    onBack: () -> Unit,
    onOpenRingMeasurement: (String) -> Unit
) {
    val product = remember(productId) { SampleStorefrontData.productById(productId) }

    if (product == null) {
        Scaffold(
            topBar = { BluePeachTopBar(title = "Product detail", onBack = onBack) },
            containerColor = BluePeachColors.surfacePlain
        ) { innerPadding ->
            BluePeachEmptyState(
                title = "Product not found",
                message = "This placeholder item is unavailable in local sample data.",
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            )
        }
        return
    }

    var selectedImageIndex by remember(product.id) { mutableIntStateOf(0) }
    val gallery = remember(product.id) {
        if (product.galleryImageUrls.isEmpty()) listOf(product.primaryImageUrl)
        else product.galleryImageUrls
    }

    Scaffold(
        topBar = { BluePeachTopBar(title = "Product detail", onBack = onBack) },
        containerColor = BluePeachColors.surfacePlain
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item { BluePeachTagBadge(text = "Blue Peach") }
                item { BluePeachInfoBadge(text = "SKU ${product.sku}") }
                item { BluePeachInfoBadge(text = if (product.stockQuantity > 0) "In stock" else "Out of stock") }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineLarge,
                color = BluePeachColors.textPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            BluePeachRatingSummary(
                rating = product.rating,
                reviewCount = product.reviewCount
            )

            Spacer(modifier = Modifier.height(14.dp))
            AsyncImage(
                model = gallery[selectedImageIndex],
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.large)
                    .border(1.dp, BluePeachColors.borderSoft, MaterialTheme.shapes.large)
                    .background(BluePeachColors.surfaceWarmSoft),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(gallery.indices.toList(), key = { it }) { index ->
                    AsyncImage(
                        model = gallery[index],
                        contentDescription = null,
                        modifier = Modifier
                            .height(72.dp)
                            .aspectRatio(1f)
                            .clip(MaterialTheme.shapes.medium)
                            .clickable { selectedImageIndex = index }
                            .border(
                                width = if (index == selectedImageIndex) 2.dp else 1.dp,
                                color = if (index == selectedImageIndex) BluePeachColors.primary else BluePeachColors.borderSoft,
                                shape = MaterialTheme.shapes.medium
                            )
                            .background(BluePeachColors.surfaceCard),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            BluePeachPriceBlock(
                priceCents = product.priceCents,
                originalPriceCents = product.originalPriceCents
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.shortDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = BluePeachColors.textSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                BluePeachPrimaryButton(
                    text = "Add to cart",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
                BluePeachSecondaryButton(
                    text = "Wishlist",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }

            if (product.isRing) {
                Spacer(modifier = Modifier.height(12.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .border(1.dp, BluePeachColors.borderSoft, MaterialTheme.shapes.medium)
                        .background(BluePeachColors.surfaceWarmSoft)
                        .padding(14.dp)
                ) {
                    Text(
                        text = "Ring measurement",
                        style = MaterialTheme.typography.titleMedium,
                        color = BluePeachColors.textPrimary
                    )
                    Text(
                        text = "This placement is reserved for future HandMeasure integration.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BluePeachColors.textSecondary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BluePeachSecondaryButton(
                        text = "Measure ring size (placeholder)",
                        onClick = { onOpenRingMeasurement(product.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .border(1.dp, BluePeachColors.borderSoft, MaterialTheme.shapes.medium)
                    .background(BluePeachColors.surfaceCard)
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Product details",
                    style = MaterialTheme.typography.titleMedium,
                    color = BluePeachColors.textPrimary
                )
                Text(
                    text = product.detailDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BluePeachColors.textSecondary
                )
                BluePeachInfoRow("Stock", "${product.stockQuantity} items")
                BluePeachInfoRow("Material", "925 silver")
                BluePeachInfoRow("Packaging", "Gift-ready box")
            }

            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Related products",
                style = MaterialTheme.typography.titleLarge,
                color = BluePeachColors.textPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(
                    SampleStorefrontData.products.filter { it.id != product.id }.take(4),
                    key = { it.id }
                ) { related ->
                    BluePeachProductCard(
                        product = related,
                        onClick = {},
                        modifier = Modifier.fillParentMaxWidth(0.58f),
                        showWishlistIcon = false
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Reviews",
                style = MaterialTheme.typography.titleLarge,
                color = BluePeachColors.textPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            SampleStorefrontData.featuredReviews
                .filter { it.productId == product.id || it.rating >= 4 }
                .take(2)
                .forEach { review ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .border(1.dp, BluePeachColors.borderSoft, MaterialTheme.shapes.medium)
                            .background(BluePeachColors.surfaceCard)
                            .padding(14.dp)
                    ) {
                        Text(
                            text = "${review.customerName} | * ${review.rating}",
                            style = MaterialTheme.typography.labelLarge,
                            color = BluePeachColors.textPrimary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = review.message,
                            style = MaterialTheme.typography.bodyMedium,
                            color = BluePeachColors.textSecondary
                        )
                    }
                }
        }
    }
}
