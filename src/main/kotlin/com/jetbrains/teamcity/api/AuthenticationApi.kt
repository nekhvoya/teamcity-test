package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.config.UserCredentials

class AuthenticationApi(user: UserCredentials): BaseApi(EnvConfig.authEndpoint, user) {

    fun getSessionCookie(cookie: String): String {
        return requestSpecification
            .get().getCookie(cookie)
    }
}