package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.config.EnvConfig.Companion.VCS_ROOT_INSTANCES_ENDPOINT
import com.jetbrains.teamcity.data.User
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.http.ContentType.ANY
import org.apache.http.HttpStatus.SC_ACCEPTED
import org.hamcrest.Matchers

class VcsInstancesApi(user: User): BaseApi(VCS_ROOT_INSTANCES_ENDPOINT, user) {
    fun triggerCommitHook(vcsId: String) {
        requestSpecification
            .accept(ANY)
            .queryParam("locator", "vcsRoot:$vcsId")
            .post("commitHookNotification")
            .then().spec(
                ResponseSpecBuilder()
                    .expectStatusCode(Matchers.equalTo(SC_ACCEPTED))
                    .build()
            )
    }
}