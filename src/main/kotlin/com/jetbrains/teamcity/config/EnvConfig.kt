package com.jetbrains.teamcity.config

class EnvConfig {
    companion object {
        const val BASE_URL = "http://localhost:8111/"
        const val PROJECTS_URL = "http://localhost:8111/favorite/projects"
        const val CREATE_PROJECT_URL = "http://localhost:8111/admin/createObjectMenu.html"
        const val CREATE_BUILD_URL = "http://localhost:8111/admin/createObjectMenu.html"
        const val EDIT_PROJECT_URL = "http://localhost:8111/admin/editProject.html"
        const val EDIT_VCS_ROOT_URL = "http://localhost:8111/admin/editVcsRoot.html"
        const val EDIT_VCS_SETTINGS_URL = "http://localhost:8111/admin/editBuildTypeVcsRoots.html"
        const val PROJECT_CONFIGURATION_URL = "http://localhost:8111/project/"
        const val BUILD_CONFIGURATION_URL = "http://localhost:8111/buildConfiguration/"
        const val EDIT_BUILD_URL = "http://localhost:8111/admin/editBuild.html"
        const val EDIT_BUILD_RUNNER_URL = "http://localhost:8111/admin/editBuildRunners.html"
        const val EDIT_RUN_TYPE_URL = "http://localhost:8111/admin/editRunType.html"
        const val PROJECTS_ENDPOINT = "http://localhost:8111/app/rest/projects/"
        const val BUILDS_ENDPOINT = "http://localhost:8111/app/rest/buildTypes"
        const val AUTH_ENDPOINT = "http://localhost:8111/authenticationTest.html"
    }
}