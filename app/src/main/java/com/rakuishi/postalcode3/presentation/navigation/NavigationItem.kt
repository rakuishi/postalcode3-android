package com.rakuishi.postalcode3.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rakuishi.postalcode3.R

sealed class NavigationItem(
    var route: String,
    @StringRes var name: Int,
    @DrawableRes var icon: Int
) {
    object Search : NavigationItem("search", R.string.tab_search, R.drawable.ic_search)
    object Favorite : NavigationItem("favorite", R.string.tab_favorite, R.drawable.ic_favorite)
}