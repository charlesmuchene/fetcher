package com.charlesmuchene.fetcher.data

import com.charlesmuchene.fetcher.Item
import com.charlesmuchene.fetcher.Items
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class ItemsRepositoryTest {

    @get:Rule
    val tempDir = TemporaryFolder()

    private lateinit var repository: ItemsRepository

    @Before
    fun setup() {
        repository = ItemsRepository(cacheDir = tempDir.newFile(), source = TestDataSource())
    }

    @Test
    fun `transform filters out items with null names`() {
        val items = listOf(Item(id = 1, listId = 1, name = null))

        val result = repository.transformItems(items = items)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `transform groups and sorts by list id first then name`() {
        val items = listOf(
            Item(id = 1, listId = 1, name = "Id 1"),
            Item(id = 2, listId = 2, name = "Id 2"),
            Item(id = 10, listId = 2, name = "Id 10"),
        )

        val result = repository.transformItems(items)

        assertEquals(1, result[1]!!.size)
        assertEquals(2, result[2]!!.size)

        val listId2Items = result[2]!!
        assertEquals(2, listId2Items[0].id)
        assertEquals(10, listId2Items[1].id)
    }

    private class TestDataSource : ItemsDataSource {
        override suspend fun fetchItems(): Items = emptyList()
    }
}