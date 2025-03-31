package com.example.dessertclicker.data

import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList

data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDesertIndex: Int = 0,
    val currentDessertPrice: Int = dessertList[currentDesertIndex].price,
    @DrawableRes val currentDessertImageId: Int = dessertList[currentDesertIndex].imageId,
)