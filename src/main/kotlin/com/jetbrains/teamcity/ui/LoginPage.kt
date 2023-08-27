package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.BASE_URL
import com.jetbrains.teamcity.data.User
import org.openqa.selenium.By.className
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class LoginPage: BasePage(id("loginPage"), "Login Page", BASE_URL) {
    private val usernameInput: SelenideElement = `$`(id("username"))
    private val passwordInput: SelenideElement = `$`(id("password"))
    private val loginButton: SelenideElement = `$`(className("loginButton"))

    companion object {
        val log: Logger = getLogger(LoginPage::class.java.simpleName)
    }

    fun loginAs(user: User) {
        typeUsername(user.username)
        typePassword(user.password)
        clickLogin()
    }

    fun typeUsername(username: String) {
        log.info("Typing $username in Username input on $pageName")
        usernameInput.type(username)
    }

    fun typePassword(password: String) {
        log.info("Typing password in Password input on $pageName")
        passwordInput.type(password)
    }

    fun clickLogin() {
        log.info("Clicking Login button on $pageName")
        loginButton.click()
    }
}