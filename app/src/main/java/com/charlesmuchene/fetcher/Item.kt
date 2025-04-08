package com.charlesmuchene.fetcher

typealias Items = List<Item>
typealias ItemGroups = Map<Int, Items>

data class Item(val id: Int, val listId: Int, val name: String? = null)
