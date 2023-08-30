package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.config.UserAccounts.Companion.users
import org.testng.annotations.Test

class LoginTest: BaseTest() {
    @Test
    fun setUpNewBuild() {
        loginPage.open()
        loginPage.shouldBeOpened()
        loginPage.loginAs(users.getValue("ADMIN"))

        projectsPage.shouldBeOpened()
        projectsPage.shouldHaveLoggedInUser(users.getValue("ADMIN"))
    }
}