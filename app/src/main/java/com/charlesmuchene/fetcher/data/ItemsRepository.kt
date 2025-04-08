package com.charlesmuchene.fetcher.data

import com.charlesmuchene.fetcher.Item
import com.charlesmuchene.fetcher.ItemGroups
import com.charlesmuchene.fetcher.Items
import java.io.File

class ItemsRepository(
    private val cacheDir: File,
    private val source: ItemsDataSource = ItemsDataSource(cacheDir = cacheDir),
) {
    suspend fun fetchItems(): ItemGroups {
        val items = source.fetchItems()
        return transformItems(items)
    }

    fun transformItems(items: Items): ItemGroups = items
        .filterNot { it.name.isNullOrBlank() }
        .sortedWith(
            compareBy<Item> { it.listId }
                .thenBy { it.name?.split(" ")[1]?.toInt() }
        )
        .groupBy { it.listId }
}