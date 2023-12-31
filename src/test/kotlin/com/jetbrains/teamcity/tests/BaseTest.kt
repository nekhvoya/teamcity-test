package com.jetbrains.teamcity.tests

import com.codeborne.selenide.WebDriverRunner.getWebDriver
import com.codeborne.selenide.testng.BrowserPerTest
import com.jetbrains.teamcity.Cookie.Companion.REMEMBER_ME
import com.jetbrains.teamcity.Cookie.Companion.SESSION
import com.jetbrains.teamcity.api.AuthenticationApi
import com.jetbrains.teamcity.config.UserAccounts.Companion.users
import com.jetbrains.teamcity.data.User
import com.jetbrains.teamcity.ui.*
import org.openqa.selenium.Cookie
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import java.lang.reflect.Method

@Listeners(BrowserPerTest::class)
abstract class BaseTest {
    protected lateinit var currentUser: User

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
    protected val administrationPage = AdministrationPage()
    protected val createUserPage = CreateUserPage()
    protected val agentsOverviewPage = AgentsOverviewPage()
    protected val agentDetailsPage = AgentDetailsPage()
    protected val editRequirementsPage = EditRequirementsPage()
    protected val projectSetupPage = ProjectSetupPage()
    protected val editTriggersPage = EditTriggersPage()

    companion object {
        val log: Logger = LoggerFactory.getLogger(BaseTest::class.java.simpleName)
    }

    @BeforeMethod
    fun setUpUser(method: Method) {
        val currentUserType: UserAccount? = method.getAnnotation(UserAccount::class.java)
        if (currentUserType == null) {
            log.warn("Test wasn't annotated with user to login with, login steps should be performed in the test")
            return
        }
        currentUser = users.getValue(currentUserType.user)
        loginPage.open()
        val cookies: Map<String, String> = AuthenticationApi(currentUser).getSessionCookies(true)
        val sessionCookie: String = cookies.getValue(SESSION)
        val rememberMeCookie: String = cookies.getValue(REMEMBER_ME)
        getWebDriver().manage().addCookie(Cookie(SESSION, sessionCookie))
        getWebDriver().manage().addCookie(Cookie(REMEMBER_ME, rememberMeCookie))
    }
}