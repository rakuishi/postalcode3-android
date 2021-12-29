package com.rakuishi.postalcode3.presentation.ui.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rakuishi.postalcode3.R
import com.rakuishi.postalcode3.database.PostalCode
import com.rakuishi.postalcode3.presentation.component.EmptyView
import com.rakuishi.postalcode3.presentation.component.PostalCodeListItem
import com.rakuishi.postalcode3.presentation.component.SearchBar
import com.rakuishi.postalcode3.presentation.theme.AppTheme
import timber.log.Timber

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    Scaffold(
        topBar = {
            SearchBar(
                viewModel.query.value,
                onQueryChanged = { viewModel.onQueryChanged(it) },
                onKeyboardDone = { viewModel.search() }
            )
        }
    ) {
        AppTheme {
            Surface {
                if (viewModel.postalCodeList.value.isEmpty()) {
                    EmptyView(stringResource(id = R.string.empty_search))
                } else {
                    PostalCodeList(viewModel.postalCodeList.value)
                }
            }
        }
    }
}

@Composable
private fun PostalCodeList(items: List<PostalCode>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(items) { _, postalCode ->
            PostalCodeListItem(
                postalCode = postalCode,
                onClicked = { Timber.d("$postalCode") }
            )
        }
    }
}