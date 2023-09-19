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
        const val BUILD_CONFIGURATION_URL = "http://localhost:8111/buildConfiguration/"
        const val EDIT_BUILD_URL = "http://localhost:8111/admin/editBuild.html"
        const val EDIT_BUILD_RUNNER_URL = "http://localhost:8111/admin/editBuildRunners.html"
        const val EDIT_RUN_TYPE_URL = "http://localhost:8111/admin/editRunType.html"
        const val EDIT_REQUIREMENTS_URL = "http://localhost:8111/admin/editRequirements.html"
        const val EDIT_TRIGGERS_URL = "http://localhost:8111/admin/editTriggers.html"
        const val ADMINISTRATION_URL = "http://localhost:8111/admin/admin.html"
        const val CREATE_USER_URL = "http://localhost:8111/admin/createUser.html"
        const val AGENTS_OVERVIEW_URL = "http://localhost:8111/agents/overview"
        const val AGENTS_DETAILS_URL = "http://localhost:8111/agent/"
        const val PROJECT_SETUP_URL = "http://localhost:8111/admin/objectSetup.html"
        const val PROJECTS_ENDPOINT = "http://localhost:8111/app/rest/projects/"
        const val BUILDS_ENDPOINT = "http://localhost:8111/app/rest/buildTypes"
        const val USERS_ENDPOINT = "http://localhost:8111/app/rest/users"
        const val AGENTS_ENDPOINT = "http://localhost:8111/app/rest/agents"
        const val VCS_ENDPOINT = "http://localhost:8111/app/rest/vcs-roots"
        const val VCS_ROOT_INSTANCES_ENDPOINT = "http://localhost:8111/app/rest/vcs-root-instances"
        const val AUTH_ENDPOINT = "http://localhost:8111/authenticationTest.html"
    }
}