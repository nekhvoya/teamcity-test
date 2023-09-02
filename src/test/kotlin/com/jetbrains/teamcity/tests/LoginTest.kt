package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.config.UserAccounts.Companion.users
import org.testng.annotations.Test

class LoginTest: BaseTest() {
    @Test
    fun login() {
        loginPage.open()
        loginPage.shouldBeOpened()
        loginPage.loginAs(users.getValue("ADMIN"))

        projectsPage.shouldBeOpened()
        projectsPage.header.shouldHaveLoggedInUser(users.getValue("ADMIN"))
        projectsPage.header.shouldDisplayAdministration()
    }
}