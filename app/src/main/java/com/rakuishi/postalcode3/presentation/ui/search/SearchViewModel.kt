package com.rakuishi.postalcode3.presentation.ui.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rakuishi.postalcode3.BuildConfig
import com.rakuishi.postalcode3.database.PostalCode
import com.rakuishi.postalcode3.repository.PostalCodeRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: PostalCodeRepository) : ViewModel() {

    val postalCodeList: MutableState<List<PostalCode>> = mutableStateOf(ArrayList())
    val query: MutableState<String> = mutableStateOf("")

    init {
        // TODO: Remove this code later
        if (BuildConfig.DEBUG) {
            query.value = "北海道"
            search()
        }
    }

    fun search() {
        viewModelScope.launch {
            val result = repository.search(query.value)
            postalCodeList.value = result
        }
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }
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