package com.jetbrains.teamcity.tests

import com.codeborne.selenide.WebDriverRunner.getWebDriver
import com.codeborne.selenide.testng.BrowserPerTest
import com.jetbrains.teamcity.api.AuthenticationApi
import com.jetbrains.teamcity.config.UserCredentials
import com.jetbrains.teamcity.ui.*
import org.openqa.selenium.Cookie
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import java.lang.reflect.Method

@Listeners(BrowserPerTest::class)
abstract class BaseTest {
    protected lateinit var currentUser: UserCredentials

    protected val loginPage = LoginPage()
    protected val projectsPage = ProjectsPage()
    protected val createProjectPage = CreateProjectPage()
    protected val createBuildPage = CreateBuildPage()
    protected val editProjectPage = EditProjectPage()
    protected val editVcsRootPage = EditVcsRootPage()
    protected val editVcsSettingsPage = EditVcsSettingsPage()
    protected val buildConfigurationPage = BuildConfigurationPage()
    protected val editBuildPage = EditBuildPage()
    protected val editBuildRunnersPage = EditBuildRunnersPage()
    protected val newBuildStepPage = NewBuildStepPage()

    companion object {
        val log: Logger = LoggerFactory.getLogger(BaseTest::class.java.simpleName)
    }

    @BeforeMethod
    fun setUpUser(method: Method) {
        val currentUserType: User? = method.getAnnotation(User::class.java)
        if (currentUserType == null) {
            log.warn("Test wasn't annotated with user to login with, login steps should be performed in the test")
            return
        }
        currentUser = currentUserType.user
        loginPage.open()
        val sessionCookie: String = AuthenticationApi(currentUser).getSessionCookie("TCSESSIONID")
        getWebDriver().manage().addCookie(Cookie("TCSESSIONID", sessionCookie))
    }
}