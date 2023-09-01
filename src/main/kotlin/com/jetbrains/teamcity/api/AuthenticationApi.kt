package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.data.User
import io.restassured.builder.ResponseSpecBuilder
import org.apache.http.HttpStatus.SC_OK
import org.hamcrest.Matchers.equalTo

class AuthenticationApi(user: User): BaseApi(EnvConfig.AUTH_ENDPOINT, user) {

    fun getSessionCookies(remember: Boolean = false): Map<String, String> {
        return requestSpecification
            .param("remember", remember)
            .get().then().spec(
                ResponseSpecBuilder()
                    .expectStatusCode(equalTo(SC_OK))
                    .build()
            ).extract().cookies()
    }
}