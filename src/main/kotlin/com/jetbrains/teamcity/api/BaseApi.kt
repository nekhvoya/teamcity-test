package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.data.User
import io.restassured.RestAssured.given
import io.restassured.filter.log.LogDetail.*
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType.JSON
import io.restassured.specification.RequestSpecification

abstract class BaseApi(apiUrl: String, user: User) {
    protected val requestSpecification: RequestSpecification

    init {
        requestSpecification = given()
            .baseUri(apiUrl)
            .auth().basic(user.username, user.password)
            .filters(RequestLoggingFilter(URI), RequestLoggingFilter(METHOD), RequestLoggingFilter(BODY), ResponseLoggingFilter(BODY))
            .contentType(JSON)
            .accept(JSON)
    }
}
