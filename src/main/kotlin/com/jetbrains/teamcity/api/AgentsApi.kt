package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.data.User
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.http.ContentType.ANY
import io.restassured.http.ContentType.TEXT
import org.apache.http.HttpStatus
import org.hamcrest.Matchers.anyOf
import org.hamcrest.Matchers.equalTo

class AgentsApi(user: User): BaseApi(EnvConfig.AGENTS_ENDPOINT, user) {
    fun enableAgent(agentName: String, enable: Boolean) {
        requestSpecification
            .contentType(TEXT)
            .accept(ANY)
            .body(enable)
            .put("name:$agentName/enabled")
            .then().spec(
                ResponseSpecBuilder()
                    .expectStatusCode(equalTo(HttpStatus.SC_OK))
                    .build()
            )
    }

    fun deleteAgent(agentName: String) {
        requestSpecification
            .delete("name:$agentName")
            .then().spec(
                ResponseSpecBuilder()
                    .expectStatusCode(
                        anyOf(
                            equalTo(HttpStatus.SC_NO_CONTENT)
                        )
                    )
                    .build()
            )
    }
}