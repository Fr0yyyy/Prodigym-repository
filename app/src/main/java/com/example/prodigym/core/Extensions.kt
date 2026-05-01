package com.example.prodigym.core

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { word ->
        word.replaceFirstChar { it.uppercase() }
    }
