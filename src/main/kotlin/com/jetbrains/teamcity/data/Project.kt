package com.jetbrains.teamcity.data

data class Project(
    val id: String,
    val name: String,
    val description: String? = null,
    val parentProject: ParentProject = ParentProject("id:_Root"),
    val copyAllAssociatedSettings: Boolean = true
) {
    constructor(name: String): this(name, name)
}
