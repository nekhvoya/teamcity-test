package com.jetbrains.teamcity.config

class EnvConfig {
    companion object {
        val baseUrl = "http://localhost:8111/"
        val projectsUrl = "http://localhost:8111/favorite/projects"
        val createProjectUrl = "http://localhost:8111/admin/createObjectMenu.html"
        val createBuildUrl = "http://localhost:8111/admin/createObjectMenu.html"
        val editProjectUrl = "http://localhost:8111/admin/editProject.html"
        val editVcsRootUrl = "http://localhost:8111/admin/editVcsRoot.html"
        val editVcsSettingsUrl = "http://localhost:8111/admin/editBuildTypeVcsRoots.html"
        val buildConfigurationsUrl = "http://localhost:8111/buildConfiguration/"
        val editBuildUrl = "http://localhost:8111/admin/editBuild.html"
        val editBuildRunnersUrl = "http://localhost:8111/admin/editBuildRunners.html"
        val editRunTypeUrl = "http://localhost:8111/admin/editRunType.html"
        val projectsEndpoint = "http://localhost:8111/app/rest/projects/"
        val buildsEndpoint = "http://localhost:8111/app/rest/buildTypes"
        val authEndpoint = "http://localhost:8111/authenticationTest.html"
    }
}