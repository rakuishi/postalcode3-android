package com.rakuishi.postalcode3.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.rakuishi.postalcode3.R

@Preview(showBackground = true)
@Composable
fun SearchView(
    query: String = "",
    onQueryChanged: (String) -> Unit = {},
    onKeyboardDone: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = query,
        label = {
            Text(text = stringResource(id = R.string.empty_search))
        },
        textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
        singleLine = true,
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "")
        },
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
