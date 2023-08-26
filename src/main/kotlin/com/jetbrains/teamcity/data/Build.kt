package com.jetbrains.teamcity.data

data class Build(
    val id: String,
    val name: String,
    val project: Project? = null,
    val steps: Steps? = null
)
