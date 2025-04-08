package com.charlesmuchene.fetcher.data

import com.charlesmuchene.fetcher.Items

class ItemsRepository(
    private val source: ItemsDataSource = ItemsDataSource(),
) {
    suspend fun fetchItems(): Items {
        return source.fetchItems()
    }
}