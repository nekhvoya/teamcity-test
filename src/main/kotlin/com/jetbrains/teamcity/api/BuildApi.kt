package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.data.Build
import com.jetbrains.teamcity.data.User
import io.restassured.builder.ResponseSpecBuilder
import org.apache.http.HttpStatus.SC_OK
import org.hamcrest.Matchers.equalTo

class BuildApi(user: User): BaseApi(EnvConfig.BUILDS_ENDPOINT, user) {

    fun createBuild(build: Build) {
        requestSpecification
            .body(build)
            .post().then().spec(
                ResponseSpecBuilder()
                    .expectStatusCode(equalTo(SC_OK))
                    .build()
            )
    }
}