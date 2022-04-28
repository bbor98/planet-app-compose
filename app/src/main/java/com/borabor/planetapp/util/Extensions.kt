package com.borabor.planetapp.util

fun Long.thousandSeparators(): String = String.format("%,d", this)
fun Double.thousandSeparators(): String = String.format("%,.2f", this)