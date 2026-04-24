package com.bluepeach.app.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Chat
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bluepeach.app.core.ui.BluePeachColors
import com.bluepeach.app.data.model.Category
import com.bluepeach.app.data.model.Product

@Composable
fun BluePeachSectionHeader(
    label: String,
    title: String,
    description: String,
    centered: Boolean = true,
    modifier: Modifier = Modifier
) {
    val alignment = if (centered) Alignment.CenterHorizontally else Alignment.Start
    val textAlign = if (centered) TextAlign.Center else TextAlign.Start

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelMedium,
            color = BluePeachColors.accent
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = BluePeachColors.textPrimary,
            textAlign = textAlign
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = BluePeachColors.textSecondary,
            textAlign = textAlign
        )
    }
}

@Composable
fun BluePeachPromoBanner(
    title: String,
    description: String,
    imageUrl: String,
    primaryCtaText: String,
    secondaryCtaText: String,
    onPrimaryClick: () -> Unit,
    onSecondaryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(1.dp, BluePeachColors.borderSoft)
    ) {
        Box {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.05f)
                    .background(BluePeachColors.surfaceBlueSoft),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0x4D000000))
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp)
            ) {
                Text(
                    text = "BLUE PEACH",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFF1F1F1)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    HeroActionButton(
                        text = primaryCtaText,
                        onClick = onPrimaryClick,
                        solid = true
                    )
                    HeroActionButton(
                        text = secondaryCtaText,
                        onClick = onSecondaryClick,
                        solid = false
                    )
                }
            }
        }
    }
}

@Composable
private fun HeroActionButton(
    text: String,
    onClick: () -> Unit,
    solid: Boolean
) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        color = if (solid) Color(0xD91B1D20) else Color.Transparent,
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.86f))
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 11.dp),
            style = MaterialTheme.typography.labelLarge,
            color = Color.White
        )
    }
}

@Composable
fun BluePeachCategoryTile(
    category: Category,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = category.imageUrl,
            contentDescription = category.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.medium)
                .border(1.dp, BluePeachColors.borderSoft, MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.name,
            style = MaterialTheme.typography.titleMedium,
            color = BluePeachColors.textPrimary,
            maxLines = 1
        )
    }
}

@Composable
fun BluePeachProductCard(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showWishlistIcon: Boolean = true
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = BluePeachColors.surfaceCard),
        border = BorderStroke(1.dp, BluePeachColors.borderSoft),
        shape = MaterialTheme.shapes.medium
    ) {
        Box {
            AsyncImage(
                model = product.primaryImageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFCFBF8),
                                Color(0xFFF2ECE3)
                            )
                        )
                    )
                    .padding(18.dp),
                contentScale = ContentScale.Fit
            )
            if (showWishlistIcon) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.86f))
                ) {
                    Icon(
                        imageVector = Icons.Rounded.FavoriteBorder,
                        contentDescription = "Yêu thích",
                        tint = BluePeachColors.textPrimary
                    )
                }
            }
            val badgeText = when {
                product.discountPercent != null -> "-${product.discountPercent}%"
                product.isBestSeller -> "Bán chạy"
                product.isNewArrival -> "Mới"
                else -> null
            }
            if (badgeText != null) {
                BluePeachTagBadge(
                    text = badgeText,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                )
            }
        }

        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                color = BluePeachColors.textPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = product.shortDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = BluePeachColors.textSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            BluePeachPriceBlock(
                priceCents = product.priceCents,
                originalPriceCents = product.originalPriceCents
            )
        }
    }
}

@Composable
fun BluePeachPriceBlock(
    priceCents: Int,
    originalPriceCents: Int? = null,
    modifier: Modifier = Modifier
) {
    val primaryText = formatVnd(priceCents)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = primaryText,
            style = MaterialTheme.typography.titleMedium,
            color = BluePeachColors.textPrimary,
            maxLines = 1
        )
        if (originalPriceCents != null && originalPriceCents > priceCents) {
            Text(
                text = formatVnd(originalPriceCents),
                style = MaterialTheme.typography.labelLarge,
                color = BluePeachColors.textTertiary,
                textDecoration = TextDecoration.LineThrough,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun BluePeachTagBadge(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = Color.White.copy(alpha = 0.88f),
        border = BorderStroke(1.dp, BluePeachColors.borderSoft)
    ) {
        Text(
            text = text.uppercase(),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            color = BluePeachColors.textPrimary
        )
    }
}

@Composable
fun BluePeachRatingSummary(
    rating: Float,
    reviewCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "%.1f sao".format(rating),
            style = MaterialTheme.typography.labelLarge,
            color = BluePeachColors.textPrimary
        )
        Text(
            text = "$reviewCount đánh giá",
            style = MaterialTheme.typography.bodyMedium,
            color = BluePeachColors.textSecondary
        )
    }
}

@Composable
fun BluePeachTrustBadge(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = Color.White.copy(alpha = 0.72f),
        border = BorderStroke(1.dp, BluePeachColors.borderSoft)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = BluePeachColors.textPrimary
        )
    }
}

@Composable
fun BluePeachInfoBadge(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = BluePeachColors.surfaceWarmSoft,
        border = BorderStroke(1.dp, BluePeachColors.borderSoft)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelLarge,
            color = BluePeachColors.textSecondary
        )
    }
}

@Composable
fun BluePeachSupportRow(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        color = Color.White.copy(alpha = 0.86f),
        border = BorderStroke(1.dp, BluePeachColors.borderSoft)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.Chat,
                contentDescription = null,
                tint = BluePeachColors.textPrimary
            )
            Spacer(modifier = Modifier.size(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = BluePeachColors.textPrimary
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = BluePeachColors.textSecondary
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null,
                tint = BluePeachColors.textSecondary
            )
        }
    }
}

@Composable
fun BluePeachEmptyState(
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = Color.White.copy(alpha = 0.86f),
        border = BorderStroke(1.dp, BluePeachColors.borderSoft)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, style = MaterialTheme.typography.titleLarge, color = BluePeachColors.textPrimary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = BluePeachColors.textSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun BluePeachErrorState(
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = Color(0xFFFFF7F7),
        border = BorderStroke(1.dp, Color(0xFFFFD9D9))
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, color = BluePeachColors.danger)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = message, style = MaterialTheme.typography.bodyMedium, color = BluePeachColors.textSecondary)
        }
    }
}

@Composable
fun BluePeachLoadingPlaceholder(
    title: String = "Đang tải storefront...",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            strokeWidth = 2.dp,
            color = BluePeachColors.primary
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = BluePeachColors.textSecondary
        )
    }
}

@Composable
fun BluePeachInfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium, color = BluePeachColors.textSecondary)
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = BluePeachColors.textPrimary,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(color = BluePeachColors.borderSoft)
    }
}

private fun formatVnd(value: Int): String = value.toString()
    .reversed()
    .chunked(3)
    .joinToString(".")
    .reversed() + " VND"

