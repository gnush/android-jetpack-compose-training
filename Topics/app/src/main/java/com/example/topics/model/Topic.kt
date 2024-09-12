package com.example.topics.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val title: Int,
    val numItems: Int,
    @DrawableRes val image: Int
)