package com.jetbrains.teamcity.data

import com.google.gson.annotations.SerializedName

class VcsRootEntry(
    val id: String,
    @SerializedName("vcs-root")
    val vcsRoot: VcsRoot
)
