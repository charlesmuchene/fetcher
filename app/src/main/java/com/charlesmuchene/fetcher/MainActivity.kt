package com.charlesmuchene.fetcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.charlesmuchene.fetcher.ui.ItemsViewModel
import com.charlesmuchene.fetcher.ui.ItemsView
import com.charlesmuchene.fetcher.ui.theme.FetcherTheme

class MainActivity : ComponentActivity() {

    private val viewModel = ItemsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetcherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        val items by viewModel.items.collectAsState()
                        ItemsView(items = items)
                    }
                }
            }
        }
    }
}
