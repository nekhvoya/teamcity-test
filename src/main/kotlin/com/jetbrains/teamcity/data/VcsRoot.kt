package com.jetbrains.teamcity.data

data class VcsRoot(
    val name: String,
    val url: String,
    val defaultBranch: String,
    val username: String? = null,
    val password: String? = null,
)
