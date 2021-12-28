package com.rakuishi.postalcode3.presentation.ui.search

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rakuishi.postalcode3.presentation.App
import com.rakuishi.postalcode3.presentation.theme.AppTheme

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(
            (requireContext().applicationContext as App).postalCodeRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    Surface {
                        Text(
                            text = "SearchFragment",
                            style = MaterialTheme.typography.body1
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
    fun SearchFragmentPreview() {
        AppTheme {
            Surface {
                Text(
                    text = "SearchFragment",
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}