package com.jetbrains.teamcity.data

import com.google.gson.annotations.SerializedName

data class VcsRootEntries(
    @SerializedName("vcs-root-entry") val vcsRootEntry: List<VcsRootEntry>
)
