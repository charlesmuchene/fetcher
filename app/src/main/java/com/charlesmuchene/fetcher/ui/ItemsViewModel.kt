package com.charlesmuchene.fetcher.ui

import com.charlesmuchene.fetcher.Item
import com.charlesmuchene.fetcher.Items
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ItemsViewModel {

    val items: StateFlow<Items> = MutableStateFlow(listOf(
        Item(1, 1, "Name 1"),
        Item(2, 2, "Name 2"),
        Item(3, 3, "Name 3"),
        Item(4, 4, "Name 4"),
    ))

}