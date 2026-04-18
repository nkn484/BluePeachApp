package com.bluepeach.app.feature.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items as lazyItems
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bluepeach.app.core.ui.BluePeachColors
import com.bluepeach.app.core.ui.components.BluePeachEmptyState
import com.bluepeach.app.core.ui.components.BluePeachProductCard
import com.bluepeach.app.core.ui.components.BluePeachSectionHeader
import com.bluepeach.app.core.ui.components.BluePeachTopBar
import com.bluepeach.app.data.model.Product

private const val SORT_NEWEST = "Newest"
private const val SORT_PRICE_ASC = "Price: Low to High"
private const val SORT_PRICE_DESC = "Price: High to Low"
private const val SORT_NAME = "Name A-Z"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    products: List<Product>,
    onBack: () -> Unit,
    onOpenProduct: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryId by remember { mutableStateOf("all") }
    var selectedSort by remember { mutableStateOf(SORT_NEWEST) }
    var sortExpanded by remember { mutableStateOf(false) }

    val categories = remember(products) {
        buildList {
            add("all" to "All")
            addAll(
                products
                    .map { it.categoryId }
                    .distinct()
                    .map { id ->
                        val label = id
                            .split("-")
                            .joinToString(" ") { part -> part.replaceFirstChar { c -> c.uppercaseChar() } }
                        id to label
                    }
            )
        }
    }

    val filteredProducts = remember(products, searchQuery, selectedCategoryId, selectedSort) {
        products
            .asSequence()
            .filter { product ->
                val categoryMatches = selectedCategoryId == "all" || product.categoryId == selectedCategoryId
                val keywordMatches = searchQuery.isBlank() ||
                    product.name.contains(searchQuery, ignoreCase = true) ||
                    product.shortDescription.contains(searchQuery, ignoreCase = true)
                categoryMatches && keywordMatches
            }
            .sortedWith(
                when (selectedSort) {
                    SORT_PRICE_ASC -> compareBy { it.priceCents }
                    SORT_PRICE_DESC -> compareByDescending { it.priceCents }
                    SORT_NAME -> compareBy { it.name }
                    else -> compareByDescending<Product> { it.isNewArrival }.thenByDescending { it.id }
                }
            )
            .toList()
    }

    Scaffold(
        topBar = {
            BluePeachTopBar(title = "Products", onBack = onBack)
        },
        containerColor = BluePeachColors.surfacePlain
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(BluePeachColors.surfacePlain)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BluePeachColors.surfaceWarm)
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                BluePeachSectionHeader(
                    label = "Blue Peach",
                    title = "All Products",
                    description = "Discover minimalist silver designs in a premium storefront layout."
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${filteredProducts.size} products",
                    style = MaterialTheme.typography.bodyMedium,
                    color = BluePeachColors.textTertiary,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Search") },
                    placeholder = { Text("Product name, style...") },
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Sort",
                        style = MaterialTheme.typography.labelLarge,
                        color = BluePeachColors.textSecondary
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    ExposedDropdownMenuBox(
                        expanded = sortExpanded,
                        onExpandedChange = { sortExpanded = !sortExpanded }
                    ) {
                        OutlinedTextField(
                            value = selectedSort,
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier
                                .menuAnchor()
                                .width(190.dp),
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = sortExpanded) }
                        )
                        ExposedDropdownMenu(
                            expanded = sortExpanded,
                            onDismissRequest = { sortExpanded = false }
                        ) {
                            listOf(SORT_NEWEST, SORT_PRICE_ASC, SORT_PRICE_DESC, SORT_NAME).forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        selectedSort = option
                                        sortExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    lazyItems(categories, key = { it.first }) { (id, label) ->
                        val selected = id == selectedCategoryId
                        FilterChip(
                            selected = selected,
                            onClick = { selectedCategoryId = id },
                            label = {
                                Text(
                                    text = label,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = BluePeachColors.textPrimary,
                                selectedLabelColor = BluePeachColors.surfacePlain
                            )
                        )
                    }
                }
            }

            if (filteredProducts.isEmpty()) {
                BluePeachEmptyState(
                    title = "No matching products",
                    message = "Try adjusting search keywords, category filters, or sort criteria.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 24.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    gridItems(filteredProducts, key = { it.id }) { product ->
                        BluePeachProductCard(
                            product = product,
                            onClick = { onOpenProduct(product.id) }
                        )
                    }
                }
            }
        }
    }
}
