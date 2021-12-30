package com.rakuishi.postalcode3.presentation.ui.favorite

import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rakuishi.postalcode3.R
import com.rakuishi.postalcode3.presentation.component.EmptyView
import com.rakuishi.postalcode3.presentation.component.StatusBarPaddingTopAppBar
import com.rakuishi.postalcode3.presentation.theme.AppTheme

@Composable
fun FavoriteScreen() {
    Scaffold(
        topBar = {
            StatusBarPaddingTopAppBar(text = stringResource(id = R.string.tab_favorite))
        }
    ) {
        AppTheme {
            Surface {
                EmptyView(stringResource(id = R.string.tab_favorite))
            }
        }
    }
}