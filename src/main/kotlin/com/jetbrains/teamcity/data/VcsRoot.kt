package com.jetbrains.teamcity.data

data class VcsRoot(
    val id: String? = null,
    val name: String,
    val vcsName: String? = null,
    val url: String? = null,
    val defaultBranch: String? = null,
    val project: Project? = null,
    val properties: Properties? = null
)
