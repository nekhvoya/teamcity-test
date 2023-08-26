package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.config.UserCredentials
import io.restassured.RestAssured.given
import io.restassured.filter.log.LogDetail
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.specification.RequestSpecification

abstract class BaseApi(apiUrl: String, user: UserCredentials) {
    protected val requestSpecification: RequestSpecification

    init {
        requestSpecification = given()
            .baseUri(apiUrl)
            .auth().basic(user.username, user.password)
            .filter(RequestLoggingFilter(LogDetail.URI))
            .filter(RequestLoggingFilter(LogDetail.METHOD))
            .filter(RequestLoggingFilter(LogDetail.BODY))
            .filter(ResponseLoggingFilter(LogDetail.BODY))
            .header("Content-Type", "application/json")
    }
}
