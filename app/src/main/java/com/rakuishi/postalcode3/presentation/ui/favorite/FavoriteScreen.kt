package com.rakuishi.postalcode3.presentation.ui.favorite

import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rakuishi.postalcode3.R
import com.rakuishi.postalcode3.presentation.component.EmptyView
import com.rakuishi.postalcode3.presentation.theme.AppTheme

@Composable
fun FavoriteScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.tab_favorite))
                },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            )
        }
    ) {
        AppTheme {
            Surface {
                EmptyView(stringResource(id = R.string.tab_favorite))
            }
        }
    }
}