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
import com.charlesmuchene.fetcher.ItemGroups

@Composable
fun ItemsView(itemGroups: ItemGroups, modifier: Modifier = Modifier) {
    if (itemGroups.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "No items",
                style = typography.headlineLarge,
            )
        }
    } else {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemGroups.forEach { (group, items) ->
                items(items) { item ->
                    ItemRow(item = item)
                }
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
    ItemsView(emptyMap())
}

@Preview(showSystemUi = true)
@Composable
private fun WithItems() {
    ItemsView(
        itemGroups = mapOf(
            1 to listOf(
                Item(id = 1, listId = 1, name = "Name 1"),
                Item(id = 2, listId = 1, name = "Name 2"),
            ), 2 to listOf(
                Item(id = 1, listId = 2, name = "Name 1"),
                Item(id = 2, listId = 2, name = "Name 2"),
            )
        ),
        modifier = Modifier.safeDrawingPadding()
    )
}