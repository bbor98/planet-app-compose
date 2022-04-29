package com.borabor.planetapp.model

import androidx.compose.runtime.Immutable

@Immutable
data class Author(
    val imageDrawable: Int,
    val name: String,
    val title: String,
    val description: String,
    val gitHubLink:String
)