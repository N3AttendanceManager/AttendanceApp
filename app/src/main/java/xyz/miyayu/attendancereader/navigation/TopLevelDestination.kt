package xyz.miyayu.attendancereader.navigation

import androidx.annotation.StringRes
import xyz.miyayu.attendancereader.R

enum class TopLevelDestination(
    @StringRes val titleTextId: Int
) {
    CLASS(
        titleTextId = R.string.navbar_class
    ),
    SETTING(
        titleTextId = R.string.navbar_settings
    )
}