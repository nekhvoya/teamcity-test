package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.config.UserCredentials.ADMIN
import org.testng.annotations.Test

class LoginTest: BaseTest() {

    @Test
    fun logIn() {
        loginPage.open()
        loginPage.shouldBeOpened()
        loginPage.loginAs(ADMIN)

        projectsPage.shouldBeOpened()
        projectsPage.shouldHaveLoggedInUser(ADMIN)
    }
}