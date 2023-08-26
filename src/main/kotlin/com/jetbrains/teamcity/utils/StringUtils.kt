package com.jetbrains.teamcity.utils

fun randomName(): String {
    val charPool : List<Char> = ('a'..'z') + ('A'..'Z')
    return List(10) { charPool.random() }.joinToString("")
}