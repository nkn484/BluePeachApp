package com.bluepeach.app.core.common

import com.bluepeach.app.data.model.CartLine
import com.bluepeach.app.data.model.Category
import com.bluepeach.app.data.model.CustomerReview
import com.bluepeach.app.data.model.Product

object SampleStorefrontData {
    const val featuredRingId = "ring-001"

    val homeHeroTitle: String = "Everyday shine, refined."
    val homeHeroDescription: String =
        "Blue Peach jewelry celebrates minimalist silver craftsmanship with a modern feminine spirit."
    val homeHeroImageUrl: String =
        "https://images.unsplash.com/photo-1515562141207-7a88fb7ce338?auto=format&fit=crop&w=1200&q=80"

    val introTitle: String = "Minimal silver for modern beauty"
    val introDescription: String =
        "A curated storefront where elegant lines, soft tones, and wearable pieces meet daily confidence."

    val categories: List<Category> = listOf(
        Category("rings", "Rings", "https://images.unsplash.com/photo-1543294001-f7cd5d7fb516?auto=format&fit=crop&w=600&q=80"),
        Category("earrings", "Earrings", "https://images.unsplash.com/photo-1617038220319-276d3cfab638?auto=format&fit=crop&w=600&q=80"),
        Category("necklaces", "Necklaces", "https://images.unsplash.com/photo-1611085583191-a3b181a88401?auto=format&fit=crop&w=600&q=80"),
        Category("bracelets", "Bracelets", "https://images.unsplash.com/photo-1588444650700-6f0f5f4f5938?auto=format&fit=crop&w=600&q=80"),
        Category("gifts", "Gift Sets", "https://images.unsplash.com/photo-1512909006721-3d6018887383?auto=format&fit=crop&w=600&q=80"),
        Category("accessories", "Accessories", "https://images.unsplash.com/photo-1524492449090-1e26ac83d4e8?auto=format&fit=crop&w=600&q=80")
    )

    val products: List<Product> = listOf(
        Product(
            id = "ring-001",
            sku = "BP-R-001",
            name = "Blue Peach Signature Ring",
            categoryId = "rings",
            shortDescription = "Polished 925 silver ring with soft taper silhouette.",
            detailDescription = "An everyday ring designed for comfort and subtle shine. Crafted to pair well with minimalist wardrobes.",
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
            shortDescription = "Lightweight hoops with mirror finish.",
            detailDescription = "Designed with a slim profile and smooth closure for all-day wear.",
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
            shortDescription = "Minimal pendant inspired by ocean lines.",
            detailDescription = "A modern pendant necklace with refined curve detail and adjustable chain length.",
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
            shortDescription = "Slim bracelet for everyday layering.",
            detailDescription = "A comfortable profile with polished texture for understated elegance.",
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
            shortDescription = "Stackable ring with delicate profile.",
            detailDescription = "Pair multiple rings or wear solo for a clean and feminine statement.",
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
            shortDescription = "Curated ring and necklace pairing.",
            detailDescription = "Gift-ready packaging with matching minimalist pieces from Blue Peach.",
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
            message = "The ring is elegant and comfortable. Exactly the refined style I wanted.",
            dateLabel = "12/02/2026",
            productImageUrl = products.first { it.id == "ring-001" }.primaryImageUrl
        ),
        CustomerReview(
            id = "review-2",
            productId = "earring-001",
            productName = "Luna Hoop Earrings",
            customerName = "Thu Ha",
            rating = 5,
            message = "Lightweight and polished. They pair perfectly with office and weekend outfits.",
            dateLabel = "05/02/2026",
            productImageUrl = products.first { it.id == "earring-001" }.primaryImageUrl
        ),
        CustomerReview(
            id = "review-3",
            productId = "bracelet-001",
            productName = "Peach Glow Bracelet",
            customerName = "Lan Phuong",
            rating = 4,
            message = "Beautiful bracelet with subtle shine and premium packaging.",
            dateLabel = "29/01/2026",
            productImageUrl = products.first { it.id == "bracelet-001" }.primaryImageUrl
        )
    )

    val trustPoints: List<String> = listOf(
        "925 silver quality",
        "Thoughtful craftsmanship",
        "Gift-ready packaging",
        "Secure checkout flow",
        "Comfortable for everyday wear",
        "Customer support"
    )

    val newArrivals: List<Product> = products.filter { it.isNewArrival }.ifEmpty { products.take(4) }
    val bestSellers: List<Product> = products.filter { it.isBestSeller }.ifEmpty { products.take(4) }

    val initialCart: List<CartLine> = listOf(
        CartLine(products.first { it.id == "ring-001" }, quantity = 1),
        CartLine(products.first { it.id == "bracelet-001" }, quantity = 2)
    )

    fun productById(productId: String): Product? = products.firstOrNull { it.id == productId }
}
