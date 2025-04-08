package com.charlesmuchene.fetcher

typealias Items = List<Item>

data class Item(val id: Int, val listId: Int, val name: String? = null)
