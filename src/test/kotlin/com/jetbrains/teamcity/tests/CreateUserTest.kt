package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.AdminSidebarItem.Companion.USERS
import com.jetbrains.teamcity.api.UsersApi
import com.jetbrains.teamcity.data.User
import org.testng.annotations.AfterTest
import org.testng.annotations.Test


internal class CreateUserTest: BaseTest() {
    private lateinit var createdUser: User

    @UserAccount("ADMIN")
    @Test
    fun createNewUser() {
        projectsPage.open()
        projectsPage.shouldBeOpened()

        projectsPage.header.clickAdministration()
        administrationPage.shouldBeOpened()
        administrationPage.sidebar.selectMenuItem(USERS)
        administrationPage.usersForm.shouldBeVisible()

        administrationPage.usersForm.clickCreateUserAccount()
        createUserPage.shouldBeOpened()

        createdUser = createUserPage.createRandomUser()
        administrationPage.usersForm.shouldBeVisible()
        administrationPage.usersForm.shouldHaveUserCreatedMessage()
        administrationPage.usersForm.shouldListUser(createdUser)

        administrationPage.header.logout()

        loginPage.shouldBeOpened()
        loginPage.loginAs(createdUser)
        projectsPage.shouldBeOpened()
        projectsPage.header.shouldHaveLoggedInUser(createdUser)
    }

    @AfterTest(alwaysRun = true)
    fun cleanUpProject() {
        if (this::createdUser.isInitialized) {
            UsersApi(currentUser).deleteUser(createdUser.username)
        }
    }
}

