package com.rakuishi.postalcode3.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rakuishi.postalcode3.R
import com.rakuishi.postalcode3.presentation.theme.AppTheme

@Preview(
    name = "Light Mode",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun SearchBarPreview() {
    AppTheme {
        Surface {
            SearchBar()
        }
    }
}

@Composable
fun SearchBar(
    query: String = "",
    onQueryChanged: (String) -> Unit = {},
    onKeyboardDone: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shadowElevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(id = R.string.empty_search),
                )
            },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = "")
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = { onQueryChanged.invoke("") }) {
                        Icon(Icons.Filled.Close, contentDescription = "")
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            onValueChange = {
                onQueryChanged.invoke(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onKeyboardDone.invoke()
                    focusManager.clearFocus()
                }
            )
        )
    }
}
