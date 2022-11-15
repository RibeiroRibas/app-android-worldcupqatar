package com.ribeiroribas.worldcupqatar.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ribeiroribas.worldcupqatar.R

sealed class Screens(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val drawable: Int
) {
    object Matches : Screens("matches", R.string.matches, R.drawable.ball)
    object Groups : Screens("groups", R.string.groups, R.drawable.ic_groups)
    object Receba : Screens("receba", R.string.receba, R.drawable.the_best_of_the_world)
}