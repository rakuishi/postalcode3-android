package com.rakuishi.postalcode3.presentation.ui.detail

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rakuishi.postalcode3.database.PostalCode
import com.rakuishi.postalcode3.presentation.theme.AppTheme

class DetailActivity : ComponentActivity() {

    companion object {
        private const val KEY_POSTAL_CODE = "postal_code"

        fun start(context: Context, postalCode: PostalCode) {
            context.startActivity(Intent(context, DetailActivity::class.java).also {
                it.putExtra(KEY_POSTAL_CODE, postalCode)
            })
        }
    }

    private val postalCode: PostalCode by lazy {
        intent.getSerializableExtra(KEY_POSTAL_CODE) as PostalCode
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface {
                    DetailScreen(
                        postalCode,
                        onNavigationIconClicked = { onBackPressed() }
                    )
                }
            }
        }
    }
}

@Composable
fun DetailScreen(
    postalCode: PostalCode,
    onNavigationIconClicked: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = postalCode.name)
                },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                navigationIcon = {
                    IconButton(onClick = { onNavigationIconClicked.invoke() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    ) {
        AppTheme {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 16.dp),
                ) {
                    Text(
                        text = postalCode.hyphenedCode,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = postalCode.prefecturePron,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = postalCode.prefecture,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = postalCode.cityPron,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = postalCode.city,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = postalCode.streetPron,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = postalCode.street,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

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
fun DetailScreenPreview() {
    val postalCode = PostalCode(
        1,
        "0640941",
        "北海道",
        "札幌市中央区",
        "旭ケ丘",
        "ほっかいどう",
        "さっぽろしちゅうおうく",
        "あさひがおか"
    )

    AppTheme {
        Surface {
            DetailScreen(postalCode)
        }
    }
}
