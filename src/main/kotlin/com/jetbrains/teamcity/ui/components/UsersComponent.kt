package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.data.User
import org.openqa.selenium.By.id
import org.openqa.selenium.By.xpath
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class UsersComponent: BaseComponent(id("userTable"), "Users Form") {
    private val createAccountButton: SelenideElement = `$`(".usersActions .addNew")
    private val userCreatedMessage: SelenideElement = `$`(id("message_userCreated"))
    private val userListRow = { user: String ->
        `$`(xpath("//table[contains(@class, 'userList')]//tr[.//td[contains(@class, 'username') and contains(., '$user')]]")) }

    companion object {
        val log: Logger = getLogger(UsersComponent::class.java.simpleName)
    }

    fun clickCreateUserAccount() {
        log.info("Clicking Create User Account button on $componentName")
        createAccountButton.click()
    }

    fun shouldHaveUserCreatedMessage() {
        log.info("Checking if user created message is visible on $componentName")
        userCreatedMessage.shouldBe(visible)
    }

    fun shouldListUser(user: User) {
        log.info("Checking if user ${user.username} is listed on $componentName")
        userListRow(user.username.lowercase()).shouldBe(visible)
    }
}