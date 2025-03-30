package com.example.setsofbricks.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.setsofbricks.R

data class BricksSet(
    @DrawableRes val iconResourceId: Int,
    @StringRes val iconDescriptionResourceId: Int,
    @StringRes val nameResourceId: Int,
    @StringRes val appearanceResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @StringRes val sizeResourceId: Int
)

val bricksSets = listOf(
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_01_name, R.string.bricks_set_01_appearance, R.string.bricks_set_01_model_description, R.string.bricks_set_01_model_size),
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_02_name, R.string.bricks_set_02_appearance, R.string.bricks_set_02_model_description, R.string.bricks_set_02_model_size),
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_03_name, R.string.bricks_set_03_appearance, R.string.bricks_set_03_model_description, R.string.bricks_set_03_model_size),
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_04_name, R.string.bricks_set_04_appearance, R.string.bricks_set_04_model_description, R.string.bricks_set_04_model_size),
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_05_name, R.string.bricks_set_05_appearance, R.string.bricks_set_05_model_description, R.string.bricks_set_05_model_size),
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_06_name, R.string.bricks_set_06_appearance, R.string.bricks_set_06_model_description, R.string.bricks_set_06_model_size),
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_07_name, R.string.bricks_set_07_appearance, R.string.bricks_set_07_model_description, R.string.bricks_set_07_model_size),
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_08_name, R.string.bricks_set_08_appearance, R.string.bricks_set_08_model_description, R.string.bricks_set_08_model_size), // TODO: add ferengi
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_09_name, R.string.bricks_set_09_appearance, R.string.bricks_set_09_model_description, R.string.bricks_set_09_model_size),
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_10_name, R.string.bricks_set_10_appearance, R.string.bricks_set_10_model_description, R.string.bricks_set_10_model_size),
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_11_name, R.string.bricks_set_11_appearance, R.string.bricks_set_11_model_description, R.string.bricks_set_11_model_size),
    BricksSet(R.drawable.starfleet, R.string.starfleet_icon_description, R.string.bricks_set_12_name, R.string.bricks_set_12_appearance, R.string.bricks_set_12_model_description, R.string.bricks_set_12_model_size),
    BricksSet(R.drawable.romulan, R.string.romulan_icon_description, R.string.bricks_set_13_name, R.string.bricks_set_13_appearance, R.string.bricks_set_13_model_description, R.string.bricks_set_13_model_size),
    BricksSet(R.drawable.klingon, R.string.klingon_icon_description, R.string.bricks_set_14_name, R.string.bricks_set_14_appearance, R.string.bricks_set_14_model_description, R.string.bricks_set_14_model_size)
)