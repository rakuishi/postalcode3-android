package com.rakuishi.postalcode3.presentation.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rakuishi.postalcode3.database.PostalCode
import com.rakuishi.postalcode3.presentation.component.PostalCodeListItem
import com.rakuishi.postalcode3.presentation.component.SearchBar
import com.rakuishi.postalcode3.presentation.theme.AppTheme
import timber.log.Timber

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    AppTheme {
        Surface {
            Column(modifier = Modifier.fillMaxSize()) {
                SearchBar(
                    viewModel.query.value,
                    onQueryChanged = { viewModel.onQueryChanged(it) },
                    onKeyboardDone = { viewModel.search() }
                )
                PostalCodeList(viewModel.postalCodeList.value)
            }
        }
    }
}

@Composable
private fun PostalCodeList(items: List<PostalCode>) {
    LazyColumn {
        itemsIndexed(items) { _, postalCode ->
            PostalCodeListItem(
                postalCode = postalCode,
                onClicked = { Timber.d("$postalCode") }
            )
        }
    }
}