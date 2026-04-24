package com.bluepeach.app.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import com.bluepeach.app.core.common.SampleStorefrontData
import com.bluepeach.app.core.ui.BluePeachColors
import com.bluepeach.app.core.ui.components.BluePeachCategoryTile
import com.bluepeach.app.core.ui.components.BluePeachErrorState
import com.bluepeach.app.core.ui.components.BluePeachInfoBadge
import com.bluepeach.app.core.ui.components.BluePeachLoadingPlaceholder
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
    viewModel: HomeViewModel = viewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value.withSampleFallback()

    Scaffold(
        topBar = { BluePeachTopBar(title = "Trang chủ") },
        containerColor = BluePeachColors.surfacePlain
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(BluePeachColors.surfacePlain)
        ) {
            if (state.isLoading) {
                item {
                    BluePeachLoadingPlaceholder(
                        title = "Đang tải storefront...",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            state.errorMessage?.let { message ->
                item {
                    BluePeachErrorState(
                        title = "Chưa kết nối được backend",
                        message = message,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            item {
                SectionSurface(warm = false) {
                    BluePeachPromoBanner(
                        title = state.banner?.title ?: SampleStorefrontData.homeHeroTitle,
                        description = state.banner?.description ?: SampleStorefrontData.homeHeroDescription,
                        imageUrl = state.banner?.imageUrl ?: SampleStorefrontData.homeHeroImageUrl,
                        primaryCtaText = state.banner?.ctaText ?: "Mua ngay",
                        secondaryCtaText = "Xem nhẫn",
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
                        description = SampleStorefrontData.introDescription,
                        centered = false
                    )
                }
            }

            item {
                SectionSurface(warm = false) {
                    BluePeachSectionHeader(
                        label = "Danh mục",
                        title = "Mua theo danh mục",
                        description = "Các nhóm trang sức được sắp xếp lại cho thao tác trên màn hình nhỏ.",
                        centered = false
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CategoryGrid(
                        categories = state.categories,
                        onOpenProducts = onOpenProducts
                    )
                }
            }

            item {
                ProductGridSection(
                    warm = true,
                    label = "Hàng mới",
                    title = "Thiết kế mới cho vẻ đẹp mỗi ngày",
                    description = "Các mẫu mới theo tinh thần tối giản, nữ tính và dễ phối đồ.",
                    products = state.newArrivals,
                    onOpenProduct = onOpenProduct
                )
            }

            item {
                SectionSurface(warm = false) {
                    BluePeachSectionHeader(
                        label = "Editorial",
                        title = "Everyday shine, refined.",
                        description = "Khối nội dung thương hiệu được rút gọn từ web cho nhịp cuộn mobile.",
                        centered = false
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BluePeachPromoBanner(
                        title = "Một cách nhẹ nhàng hơn để đeo bạc",
                        description = "Những thiết kế tinh gọn, dễ hòa vào tủ đồ hằng ngày.",
                        imageUrl = "https://images.unsplash.com/photo-1515377905703-c4788e51af15?auto=format&fit=crop&w=1200&q=80",
                        primaryCtaText = "Xem bộ chọn",
                        secondaryCtaText = "Xem sản phẩm",
                        onPrimaryClick = onOpenProducts,
                        onSecondaryClick = onOpenProducts
                    )
                }
            }

            item {
                ProductGridSection(
                    warm = true,
                    label = "Bán chạy",
                    title = "Được khách hàng Blue Peach yêu thích",
                    description = "Các sản phẩm nổi bật được đặt trong lưới hai cột cân đối.",
                    products = state.bestSellers,
                    onOpenProduct = onOpenProduct
                )
            }

            item {
                SectionSurface(warm = false) {
                    BluePeachSectionHeader(
                        label = "Đánh giá nổi bật",
                        title = "Khách hàng nói gì",
                        description = "Các đánh giá được xếp dọc để đọc rõ trên màn hình nhỏ.",
                        centered = false
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        state.featuredReviews.forEach { review ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = BluePeachColors.surfaceCard,
                                        shape = RoundedCornerShape(18.dp)
                                    )
                                    .padding(14.dp)
                            ) {
                                Text(
                                    text = review.productName,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = BluePeachColors.textPrimary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "${review.rating} sao | ${review.customerName}",
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
                        label = "Vì sao chọn Blue Peach",
                        title = "Thiết kế tinh tế, trải nghiệm chỉn chu",
                        description = "Các điểm tin cậy chính được giữ nhất quán với storefront web.",
                        centered = false
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        SampleStorefrontData.trustPoints.chunked(2).forEach { rowItems ->
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                rowItems.forEach { point ->
                                    BluePeachTrustBadge(text = point, modifier = Modifier.weight(1f))
                                }
                                if (rowItems.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }

            item {
                SectionSurface(warm = true) {
                    Text(
                        text = "Cần tư vấn chọn quà?",
                        style = MaterialTheme.typography.titleLarge,
                        color = BluePeachColors.textPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    BluePeachSupportRow(
                        title = "Hỗ trợ khách hàng",
                        subtitle = "Tư vấn kiểu dáng, kích thước và tình trạng đơn hàng."
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        BluePeachInfoBadge("Sẵn sàng làm quà", modifier = Modifier.weight(1f))
                        BluePeachInfoBadge("Mua sắm an toàn", modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    BluePeachInfoBadge("Đổi trả dễ dàng", modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
private fun CategoryGrid(
    categories: List<com.bluepeach.app.data.model.Category>,
    onOpenProducts: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        categories.chunked(2).forEach { rowItems ->
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                rowItems.forEach { category ->
                    BluePeachCategoryTile(
                        category = category,
                        onClick = onOpenProducts,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ProductGridSection(
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
            description = description,
            centered = false
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            products.take(4).chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowItems.forEach { product ->
                        BluePeachProductCard(
                            product = product,
                            onClick = { onOpenProduct(product.id) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionSurface(
    warm: Boolean,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clipToBounds()
            .background(if (warm) BluePeachColors.surfaceWarm else BluePeachColors.surfacePlain)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        content()
    }
}
