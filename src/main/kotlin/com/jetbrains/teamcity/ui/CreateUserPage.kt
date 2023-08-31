package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.CREATE_USER_URL
import com.jetbrains.teamcity.data.User
import com.jetbrains.teamcity.utils.randomString
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CreateUserPage: BasePage(id("profilePage"), "Create User Page", CREATE_USER_URL) {
    private val usernameInput: SelenideElement = `$`(id("input_teamcityUsername"))
    private val passwordInput: SelenideElement = `$`(id("password1"))
    private val confirmPasswordInput: SelenideElement = `$`(id("retypedPassword"))
    private val createButton: SelenideElement = `$`("[name=submitCreateUser]")

    companion object {
        val log: Logger = LoggerFactory.getLogger(CreateUserPage::class.java.simpleName)
    }

    fun createRandomUser(): User {
        val user = User(randomString(), randomString())
        typeUsername(user.username)
        typePassword(user.password)
        typeConfirmPassword(user.password)
        clickCreateButton()
        return user
    }

    fun typeUsername(username: String) {
        log.info("Typing $username in Username input on $pageName")
        usernameInput.type(username)
    }

    fun typePassword(password: String) {
        log.info("Typing password in Password input on $pageName")
        passwordInput.type(password)
    }

    fun typeConfirmPassword(password: String) {
        log.info("Typing password in Confirm Password input on $pageName")
        confirmPasswordInput.type(password)
    }

    fun clickCreateButton() {
        log.info("Clicking Create button on $pageName")
        createButton.click()
    }
}