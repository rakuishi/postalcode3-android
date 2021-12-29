package com.rakuishi.postalcode3.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rakuishi.postalcode3.database.PostalCode
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
fun PostalCodeListItemPreview() {
    AppTheme {
        Surface {
            PostalCodeListItem(PostalCode(1, "0640941", "北海道", "札幌市中央区", "旭ケ丘"))
        }
    }
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
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = postalCode.name,
            style = MaterialTheme.typography.titleMedium
        )
    }
}