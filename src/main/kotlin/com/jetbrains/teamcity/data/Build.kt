package com.jetbrains.teamcity.data

import com.google.gson.annotations.SerializedName

data class Build(
    val id: String,
    val name: String,
    val project: Project? = null,
    val steps: Steps? = null,
    @SerializedName("vcs-root-entries") val vcsRootEntries: VcsRootEntries? = null
)
