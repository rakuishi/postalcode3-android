package com.rakuishi.postalcode3.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rakuishi.postalcode3.database.PostalCode
import com.rakuishi.postalcode3.presentation.App
import com.rakuishi.postalcode3.presentation.component.PostalCodeListItem
import com.rakuishi.postalcode3.presentation.component.SearchView
import timber.log.Timber


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
                Column {
                    SearchView(
                        viewModel.query.value,
                        onQueryChanged = { viewModel.onQueryChanged(it) },
                        onKeyboardDone = { viewModel.search() }
                    )
                    PostalCodeList(viewModel.postalCodeList.value)
                }
            }
        }
    }

    @Composable
    fun PostalCodeList(items: List<PostalCode>) {
        LazyColumn {
            itemsIndexed(items) { _, postalCode ->
                PostalCodeListItem(
                    postalCode = postalCode,
                    onClicked = { Timber.d("$postalCode") }
                )
            }
        }
    }
}