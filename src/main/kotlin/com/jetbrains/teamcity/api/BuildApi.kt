package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.config.UserCredentials
import com.jetbrains.teamcity.data.Build

class BuildApi(user: UserCredentials): BaseApi(EnvConfig.buildsEndpoint, user) {

    fun createBuild(build: Build) {
        requestSpecification
            .body(build)
            .post()
    }
}