package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.config.EnvConfig.Companion.VCS_ENDPOINT
import com.jetbrains.teamcity.data.User
import com.jetbrains.teamcity.data.VcsRoot
import io.restassured.builder.ResponseSpecBuilder
import org.apache.http.HttpStatus.SC_OK
import org.hamcrest.Matchers

class VcsApi(user: User): BaseApi(VCS_ENDPOINT, user) {

    fun createVcsRoot(vcs: VcsRoot) {
        requestSpecification
            .body(vcs)
            .post().then().spec(
                ResponseSpecBuilder()
                    .expectStatusCode(Matchers.equalTo(SC_OK))
                    .build()
            )
    }
}