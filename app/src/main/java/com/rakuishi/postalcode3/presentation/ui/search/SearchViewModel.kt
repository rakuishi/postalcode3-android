package com.rakuishi.postalcode3.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rakuishi.postalcode3.repository.PostalCodeRepository

class SearchViewModel(private val repository: PostalCodeRepository) : ViewModel() {
    // TODO: Implement the ViewModel
}

class SearchViewModelFactory(private val repository: PostalCodeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}