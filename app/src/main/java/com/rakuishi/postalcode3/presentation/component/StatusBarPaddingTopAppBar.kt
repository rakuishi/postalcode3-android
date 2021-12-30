package com.rakuishi.postalcode3.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun StatusBarPaddingTopAppBar(
    text: String,
    onNavigationIconClicked: (() -> Unit)? = null
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = AppBarDefaults.TopAppBarElevation
    ) {
        TopAppBar(
            modifier = Modifier.statusBarsPadding(),
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            title = { Text(text = text) },
            navigationIcon = if (onNavigationIconClicked != null) {
                {
                    IconButton(onClick = {
                        onNavigationIconClicked.invoke()
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                }
            } else {
                null
            }
        )
    }
}