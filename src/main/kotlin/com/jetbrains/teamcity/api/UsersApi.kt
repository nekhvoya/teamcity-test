package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.data.User
import io.restassured.builder.ResponseSpecBuilder
import org.apache.http.HttpStatus.SC_NO_CONTENT
import org.hamcrest.Matchers

class UsersApi(user: User): BaseApi(EnvConfig.USERS_ENDPOINT, user) {

    fun deleteUser(username: String) {
        requestSpecification
            .delete("username:$username")
            .then().spec(
                ResponseSpecBuilder()
                    .expectStatusCode(Matchers.equalTo(SC_NO_CONTENT))
                    .build()
            )
    }
}