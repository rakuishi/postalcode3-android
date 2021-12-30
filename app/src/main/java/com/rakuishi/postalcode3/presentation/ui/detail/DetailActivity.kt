package com.rakuishi.postalcode3.presentation.ui.detail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rakuishi.postalcode3.R
import com.rakuishi.postalcode3.database.PostalCode
import com.rakuishi.postalcode3.presentation.theme.AppTheme
import kotlinx.coroutines.launch

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
                        onNavigationIconClicked = { onBackPressed() },
                        onShareClicked = { share() },
                        onCopyClicked = { copy() },
                        onOpenInGoogleMapsClicked = { openInGoogleMaps() }
                    )
                }
            }
        }
    }

    private fun share() {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, postalCode.toString())
        intent.type = "text/plain"
        startActivity(intent)
    }

    private fun copy() {
        val clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("", postalCode.toString())
        clipboard.setPrimaryClip(clip)
    }

    private fun openInGoogleMaps() {
        val uri: Uri = Uri.parse("geo:0,0?q=" + Uri.encode(postalCode.name))
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DetailScreen(
    postalCode: PostalCode,
    onNavigationIconClicked: () -> Unit = {},
    onShareClicked: () -> Unit = {},
    onCopyClicked: () -> Unit = {},
    onOpenInGoogleMapsClicked: () -> Unit = {},
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    val toggleBottomSheet = {
        coroutineScope.launch {
            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                bottomSheetScaffoldState.bottomSheetState.expand()
            } else {
                bottomSheetScaffoldState.bottomSheetState.collapse()
            }
        }
    }
    val closeBottomSheet = {
        coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.collapse()
        }
    }

    BottomSheetScaffold(
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
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            AppTheme {
                Surface {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onShareClicked.invoke()
                                    closeBottomSheet()
                                }
                                .padding(16.dp),
                            text = stringResource(id = R.string.share)
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onCopyClicked.invoke()
                                    closeBottomSheet()
                                }
                                .padding(16.dp),
                            text = stringResource(id = R.string.copy)
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onOpenInGoogleMapsClicked.invoke()
                                    closeBottomSheet()
                                }
                                .padding(16.dp),
                            text = stringResource(id = R.string.open_in_google_maps)
                        )
                    }
                }
            }
        },
    ) {
        AppTheme {
            Surface {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 16.dp)
                ) {

                    Card(shape = RoundedCornerShape(16.dp)) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .clickable { toggleBottomSheet.invoke() }
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
    }
}

@ExperimentalMaterialApi
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
