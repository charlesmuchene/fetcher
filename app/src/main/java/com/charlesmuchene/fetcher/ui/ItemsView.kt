package com.charlesmuchene.fetcher.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.charlesmuchene.fetcher.Item
import com.charlesmuchene.fetcher.Items

@Composable
fun ItemsView(items: Items, modifier: Modifier = Modifier) {
    if (items.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "No items",
                style = typography.headlineLarge,
            )
        }
    } else {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(items) { item ->
                ItemRow(item = item)
            }
        }
    }
}

@Composable
private fun ItemRow(item: Item, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item.name?.let { Text(text = it, style = typography.titleLarge) }
        Text(text = "Id: ${item.id}", style = typography.bodyLarge, color = Color.Gray)
        HorizontalDivider()
    }
}

@Preview(showSystemUi = true)
@Composable
private fun EmptyItems() {
    ItemsView(emptyList())
}

@Preview(showSystemUi = true)
@Composable
private fun WithItems() {
    ItemsView(
        items = listOf(
            Item(1, 1, "Name 1"),
            Item(2, 2, "Name 2"),
            Item(3, 3, "Name 3"),
            Item(4, 4, "Name 4"),
        ),
        modifier = Modifier.safeDrawingPadding()
    )
}