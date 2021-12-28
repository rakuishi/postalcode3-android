package com.rakuishi.postalcode3.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rakuishi.postalcode3.database.PostalCode

@Preview(showBackground = true)
@Composable
fun PostalCodeListItemPreview() {
    PostalCodeListItem(PostalCode(1, "0640941", "北海道", "札幌市中央区", "旭ケ丘"))
}

@Composable
fun PostalCodeListItem(
    postalCode: PostalCode,
    onClicked: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .clickable { onClicked.invoke() }
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = postalCode.code,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = postalCode.name,
            style = MaterialTheme.typography.subtitle1
        )
    }
}