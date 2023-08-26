package com.jetbrains.teamcity.config

import org.openqa.selenium.Cookie

class Storage {
    companion object {
        val cookieStorage = HashMap<UserCredentials, Cookie>()
    }
}