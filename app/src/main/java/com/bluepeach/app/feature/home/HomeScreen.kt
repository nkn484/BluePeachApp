package com.bluepeach.app.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bluepeach.app.core.common.SampleStorefrontData
import com.bluepeach.app.core.ui.BluePeachColors
import com.bluepeach.app.core.ui.components.BluePeachCategoryTile
import com.bluepeach.app.core.ui.components.BluePeachInfoBadge
import com.bluepeach.app.core.ui.components.BluePeachProductCard
import com.bluepeach.app.core.ui.components.BluePeachPromoBanner
import com.bluepeach.app.core.ui.components.BluePeachSectionHeader
import com.bluepeach.app.core.ui.components.BluePeachSupportRow
import com.bluepeach.app.core.ui.components.BluePeachTopBar
import com.bluepeach.app.core.ui.components.BluePeachTrustBadge
import com.bluepeach.app.data.model.Product

@Composable
fun HomeScreen(
    onOpenProducts: () -> Unit,
    onOpenProduct: (String) -> Unit,
    onOpenCart: () -> Unit,
    onOpenAccount: () -> Unit
) {
    Scaffold(
        topBar = {
            BluePeachTopBar(
                title = "BLUE PEACH",
                onCart = onOpenCart,
                onAccount = onOpenAccount
            )
        },
        containerColor = BluePeachColors.surfacePlain
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(BluePeachColors.surfacePlain)
        ) {
            item {
                SectionSurface(warm = false) {
                    BluePeachPromoBanner(
                        title = SampleStorefrontData.homeHeroTitle,
                        description = SampleStorefrontData.homeHeroDescription,
                        imageUrl = SampleStorefrontData.homeHeroImageUrl,
                        primaryCtaText = "Shop now",
                        secondaryCtaText = "View ring",
                        onPrimaryClick = onOpenProducts,
                        onSecondaryClick = { onOpenProduct(SampleStorefrontData.featuredRingId) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            item {
                SectionSurface(warm = true) {
                    BluePeachSectionHeader(
                        label = "Blue Peach",
                        title = SampleStorefrontData.introTitle,
                        description = SampleStorefrontData.introDescription
                    )
                }
            }

            item {
                SectionSurface(warm = false) {
                    BluePeachSectionHeader(
                        label = "Categories",
                        title = "Shop by category",
                        description = "Explore curated jewelry groups inspired by the web storefront."
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        SampleStorefrontData.categories.chunked(2).forEach { rowItems ->
                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                rowItems.forEach { category ->
                                    BluePeachCategoryTile(
                                        category = category,
                                        onClick = onOpenProducts,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                                if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            item {
                ProductSection(
                    warm = true,
                    label = "New arrivals",
                    title = "Fresh designs for everyday elegance",
                    description = "Latest products with minimalist and feminine styling.",
                    products = SampleStorefrontData.newArrivals,
                    onOpenProduct = onOpenProduct
                )
            }

            item {
                SectionSurface(warm = false) {
                    BluePeachSectionHeader(
                        label = "Editorial",
                        title = "A calmer way to wear silver",
                        description = "A brand storytelling block translated from web editorial composition."
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BluePeachPromoBanner(
                        title = "Everyday shine, refined.",
                        description = "Pieces designed to pair naturally with modern wardrobe essentials.",
                        imageUrl = "https://images.unsplash.com/photo-1515377905703-c4788e51af15?auto=format&fit=crop&w=1200&q=80",
                        primaryCtaText = "View edit",
                        secondaryCtaText = "Browse products",
                        onPrimaryClick = onOpenProducts,
                        onSecondaryClick = onOpenProducts
                    )
                }
            }

            item {
                ProductSection(
                    warm = true,
                    label = "Best sellers",
                    title = "Most loved by Blue Peach shoppers",
                    description = "Top picks with strong customer preference and social proof.",
                    products = SampleStorefrontData.bestSellers,
                    onOpenProduct = onOpenProduct
                )
            }

            item {
                SectionSurface(warm = false) {
                    BluePeachSectionHeader(
                        label = "Featured reviews",
                        title = "What customers say",
                        description = "Social proof preview inspired by the web storefront review cards."
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(SampleStorefrontData.featuredReviews, key = { it.id }) { review ->
                            Column(
                                modifier = Modifier
                                    .fillParentMaxWidth(0.86f)
                                    .background(BluePeachColors.surfaceCard, MaterialTheme.shapes.medium)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = review.productName,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = BluePeachColors.textPrimary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Rating ${review.rating} | ${review.customerName}",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = BluePeachColors.textSecondary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "\"${review.message}\"",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = BluePeachColors.textSecondary
                                )
                            }
                        }
                    }
                }
            }

            item {
                SectionSurface(warm = false) {
                    BluePeachSectionHeader(
                        label = "Why Blue Peach",
                        title = "Refined design, thoughtful experience",
                        description = "Trust-focused section translated into native Android badges."
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(SampleStorefrontData.trustPoints) { point ->
                            BluePeachTrustBadge(text = point)
                        }
                    }
                }
            }

            item {
                SectionSurface(warm = true) {
                    Text(
                        text = "Need help choosing a gift?",
                        style = MaterialTheme.typography.titleLarge,
                        color = BluePeachColors.textPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BluePeachSupportRow(
                        title = "Customer support",
                        subtitle = "Get styling and order support from the Blue Peach team."
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        item { BluePeachInfoBadge("Gift-ready packaging") }
                        item { BluePeachInfoBadge("Secure shopping") }
                        item { BluePeachInfoBadge("Easy returns") }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductSection(
    warm: Boolean,
    label: String,
    title: String,
    description: String,
    products: List<Product>,
    onOpenProduct: (String) -> Unit
) {
    SectionSurface(warm = warm) {
        BluePeachSectionHeader(
            label = label,
            title = title,
            description = description
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(products, key = { it.id }) { product ->
                BluePeachProductCard(
                    product = product,
                    onClick = { onOpenProduct(product.id) },
                    modifier = Modifier.fillParentMaxWidth(0.56f)
                )
            }
        }
    }
}

@Composable
private fun SectionSurface(
    warm: Boolean,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (warm) BluePeachColors.surfaceWarm else BluePeachColors.surfacePlain)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        content()
    }
}
