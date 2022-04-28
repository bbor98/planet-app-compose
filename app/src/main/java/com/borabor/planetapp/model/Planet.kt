package com.borabor.planetapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class Planet(
    val imageDrawable:Int,
    val name:String,
    val surfaceArea: Long,
    val surfaceTemperature: List<Int>,
    val orbitDistance: Long,
    val orbitPeriod:Double,
    val mass: Long,
    val age: Long,
    val description:String
)
