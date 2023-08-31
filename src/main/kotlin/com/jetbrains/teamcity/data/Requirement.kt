package com.jetbrains.teamcity.data

data class Requirement(
    val parameter: String,
    val condition: String,
    val value: String? = null,
)
