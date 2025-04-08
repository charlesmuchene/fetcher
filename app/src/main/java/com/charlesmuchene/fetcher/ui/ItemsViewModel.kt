package com.charlesmuchene.fetcher.ui

import com.charlesmuchene.fetcher.ItemGroups
import com.charlesmuchene.fetcher.Items
import com.charlesmuchene.fetcher.data.ItemsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class ItemsViewModel(
    private val cacheDir: File,
    private val repository: ItemsRepository = ItemsRepository(cacheDir = cacheDir),
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
): CoroutineScope by CoroutineScope(dispatcher) {

    private val _items = MutableStateFlow<ItemGroups>(emptyMap())
    val items: StateFlow<ItemGroups> = _items.asStateFlow()

    fun fetch() {
        launch {
            _items.emit(repository.fetchItems())
        }
    }
}