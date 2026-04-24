package com.bluepeach.app.core.common

import com.bluepeach.app.data.model.CartLine
import com.bluepeach.app.data.model.Category
import com.bluepeach.app.data.model.CustomerReview
import com.bluepeach.app.data.model.Product

object SampleStorefrontData {
    const val featuredRingId = "ring-001"

    val homeHeroTitle: String = "Everyday shine, refined."
    val homeHeroDescription: String =
        "Trang sức bạc tối giản với tinh thần nữ tính hiện đại, được tuyển chọn cho vẻ đẹp mỗi ngày."
    val homeHeroImageUrl: String =
        "https://images.unsplash.com/photo-1515562141207-7a88fb7ce338?auto=format&fit=crop&w=1200&q=80"

    val introTitle: String = "Bạc tối giản cho vẻ đẹp hiện đại"
    val introDescription: String =
        "Không gian mua sắm được giám tuyển với đường nét thanh lịch, sắc độ dịu và dễ đeo hằng ngày."

    val categories: List<Category> = listOf(
        Category("rings", "Nhẫn", "https://images.unsplash.com/photo-1543294001-f7cd5d7fb516?auto=format&fit=crop&w=600&q=80"),
        Category("earrings", "Khuyên tai", "https://images.unsplash.com/photo-1617038220319-276d3cfab638?auto=format&fit=crop&w=600&q=80"),
        Category("necklaces", "Dây chuyền", "https://images.unsplash.com/photo-1611085583191-a3b181a88401?auto=format&fit=crop&w=600&q=80"),
        Category("bracelets", "Vòng tay", "https://images.unsplash.com/photo-1588444650700-6f0f5f4f5938?auto=format&fit=crop&w=600&q=80"),
        Category("gifts", "Quà tặng", "https://images.unsplash.com/photo-1512909006721-3d6018887383?auto=format&fit=crop&w=600&q=80"),
        Category("accessories", "Phụ kiện", "https://images.unsplash.com/photo-1524492449090-1e26ac83d4e8?auto=format&fit=crop&w=600&q=80")
    )

    val products: List<Product> = listOf(
        Product(
            id = "ring-001",
            sku = "BP-R-001",
            name = "Blue Peach Signature Ring",
            categoryId = "rings",
            shortDescription = "Nhẫn bạc 925 dáng mềm, dễ đeo hằng ngày.",
            detailDescription = "Thiết kế nhẫn tối giản, bề mặt sáng nhẹ và phom ôm tay thoải mái. Phù hợp để đeo riêng hoặc phối cùng các mẫu nhẫn mảnh khác.",
            priceCents = 149000,
            originalPriceCents = 179000,
            discountPercent = 17,
            stockQuantity = 14,
            primaryImageUrl = "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?auto=format&fit=crop&w=900&q=80",
            galleryImageUrls = listOf(
                "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?auto=format&fit=crop&w=900&q=80",
                "https://images.unsplash.com/photo-1617038220319-276d3cfab638?auto=format&fit=crop&w=900&q=80",
                "https://images.unsplash.com/photo-1515562141207-7a88fb7ce338?auto=format&fit=crop&w=900&q=80"
            ),
            rating = 4.8f,
            reviewCount = 42,
            isBestSeller = true,
            isNewArrival = true,
            isRing = true
        ),
        Product(
            id = "earring-001",
            sku = "BP-E-001",
            name = "Luna Hoop Earrings",
            categoryId = "earrings",
            shortDescription = "Khuyên tai dáng hoop nhẹ, bề mặt sáng bóng.",
            detailDescription = "Thiết kế mảnh, khóa gọn và đủ nhẹ để đeo cả ngày. Phù hợp với trang phục đi làm lẫn cuối tuần.",
            priceCents = 99000,
            stockQuantity = 22,
            primaryImageUrl = "https://images.unsplash.com/photo-1630019852942-f89202989a59?auto=format&fit=crop&w=900&q=80",
            galleryImageUrls = listOf(
                "https://images.unsplash.com/photo-1630019852942-f89202989a59?auto=format&fit=crop&w=900&q=80",
                "https://images.unsplash.com/photo-1512909006721-3d6018887383?auto=format&fit=crop&w=900&q=80"
            ),
            rating = 4.6f,
            reviewCount = 27,
            isBestSeller = true
        ),
        Product(
            id = "necklace-001",
            sku = "BP-N-001",
            name = "Wave Necklace",
            categoryId = "necklaces",
            shortDescription = "Dây chuyền mặt cong lấy cảm hứng từ sóng biển.",
            detailDescription = "Mặt dây có đường cong tinh tế, dây đeo dễ điều chỉnh để phối với nhiều kiểu cổ áo.",
            priceCents = 129000,
            stockQuantity = 9,
            primaryImageUrl = "https://images.unsplash.com/photo-1617038220319-276d3cfab638?auto=format&fit=crop&w=900&q=80",
            rating = 4.7f,
            reviewCount = 19,
            isNewArrival = true
        ),
        Product(
            id = "bracelet-001",
            sku = "BP-B-001",
            name = "Peach Glow Bracelet",
            categoryId = "bracelets",
            shortDescription = "Vòng tay mảnh, dễ phối nhiều lớp.",
            detailDescription = "Phom vòng gọn, ánh bạc nhẹ và phù hợp với phong cách thanh lịch hằng ngày.",
            priceCents = 109000,
            originalPriceCents = 129000,
            discountPercent = 15,
            stockQuantity = 18,
            primaryImageUrl = "https://images.unsplash.com/photo-1588444650700-6f0f5f4f5938?auto=format&fit=crop&w=900&q=80",
            rating = 4.5f,
            reviewCount = 33
        ),
        Product(
            id = "ring-002",
            sku = "BP-R-002",
            name = "Halo Stack Ring",
            categoryId = "rings",
            shortDescription = "Nhẫn mảnh có thể đeo riêng hoặc phối chồng.",
            detailDescription = "Dáng nhẫn thanh nhẹ, tạo điểm nhấn vừa đủ khi phối nhiều lớp hoặc đeo độc lập.",
            priceCents = 119000,
            stockQuantity = 11,
            primaryImageUrl = "https://images.unsplash.com/photo-1543294001-f7cd5d7fb516?auto=format&fit=crop&w=900&q=80",
            rating = 4.4f,
            reviewCount = 13,
            isRing = true
        ),
        Product(
            id = "gift-001",
            sku = "BP-G-001",
            name = "Daily Shine Gift Set",
            categoryId = "gifts",
            shortDescription = "Bộ quà tặng gồm nhẫn và dây chuyền tinh gọn.",
            detailDescription = "Bộ sản phẩm được đóng gói chỉn chu, phù hợp làm quà tặng trong các dịp đặc biệt.",
            priceCents = 239000,
            originalPriceCents = 279000,
            discountPercent = 14,
            stockQuantity = 6,
            primaryImageUrl = "https://images.unsplash.com/photo-1524492449090-1e26ac83d4e8?auto=format&fit=crop&w=900&q=80",
            rating = 4.9f,
            reviewCount = 11,
            isBestSeller = true
        )
    )

    val featuredReviews: List<CustomerReview> = listOf(
        CustomerReview(
            id = "review-1",
            productId = "ring-001",
            productName = "Blue Peach Signature Ring",
            customerName = "Minh Anh",
            rating = 5,
            message = "Nhẫn thanh lịch, đeo thoải mái và đúng phong cách mình tìm.",
            dateLabel = "12/02/2026",
            productImageUrl = productImageOrFallback("ring-001")
        ),
        CustomerReview(
            id = "review-2",
            productId = "earring-001",
            productName = "Luna Hoop Earrings",
            customerName = "Thu Hà",
            rating = 5,
            message = "Nhẹ, sáng và rất dễ phối đồ từ đi làm đến cuối tuần.",
            dateLabel = "05/02/2026",
            productImageUrl = productImageOrFallback("earring-001")
        ),
        CustomerReview(
            id = "review-3",
            productId = "bracelet-001",
            productName = "Peach Glow Bracelet",
            customerName = "Lan Phương",
            rating = 4,
            message = "Vòng tay đẹp, ánh bạc vừa phải và đóng gói chỉn chu.",
            dateLabel = "29/01/2026",
            productImageUrl = productImageOrFallback("bracelet-001")
        )
    )

    val trustPoints: List<String> = listOf(
        "Bạc 925 tiêu chuẩn",
        "Chế tác tỉ mỉ",
        "Đóng gói quà tặng",
        "Mua sắm an toàn",
        "Dễ đeo hằng ngày",
        "Hỗ trợ tận tâm"
    )

    val newArrivals: List<Product> = products.filter { it.isNewArrival }.ifEmpty { products.take(4) }
    val bestSellers: List<Product> = products.filter { it.isBestSeller }.ifEmpty { products.take(4) }

    val initialCart: List<CartLine> = buildList {
        products.firstOrNull { it.id == "ring-001" }?.let { add(CartLine(it, quantity = 1)) }
        products.firstOrNull { it.id == "bracelet-001" }?.let { add(CartLine(it, quantity = 2)) }
    }

    fun productById(productId: String): Product? = products.firstOrNull { it.id == productId }

    private fun productImageOrFallback(productId: String): String {
        return products.firstOrNull { it.id == productId }?.primaryImageUrl.orEmpty()
    }
}
