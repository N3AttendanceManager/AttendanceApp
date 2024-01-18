package xyz.miyayu.attendancereader.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import xyz.miyayu.attendancereader.R

enum class TopLevelDestination(
    @StringRes val titleTextId: Int,
    @DrawableRes val drawableResId: Int
) {
    CLASS(
        titleTextId = R.string.navbar_class,
        drawableResId = R.drawable.subject
    ),
    SETTING(
        titleTextId = R.string.navbar_settings,
        drawableResId = R.drawable.setting
    )
}