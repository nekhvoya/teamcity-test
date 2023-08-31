package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Condition.attribute
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.PopUpMenuItem.Companion.LOG_OUT
import com.jetbrains.teamcity.data.User
import org.openqa.selenium.By.tagName
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class Header: BaseComponent(tagName("header"), "Header") {
    private val header: SelenideElement = `$`(componentLocator)
    private val projectsButton: SelenideElement = header.`$`("[title=Projects]")
    private val administrationButton: SelenideElement = header.`$`("[data-hint-container-id=header-administration-link]")
    private val loggedInUserIcon: SelenideElement = `$`("[data-hint-container-id=header-user-menu] button")

    val popUpMenu = PopUpMenu()

    companion object {
        val log: Logger = getLogger(Header::class.java.simpleName)
    }

    fun clickProjects() {
        log.info("Clicking Projects button in $componentName")
        projectsButton.click()
    }

    fun clickAdministration() {
        log.info("Clicking Administration button in $componentName")
        administrationButton.click()
    }

    fun logout() {
        clickLoggedInUserIcon()
        popUpMenu.selectItem(LOG_OUT)
    }

    fun clickLoggedInUserIcon() {
        log.info("Clicking logged in user icon in $componentName")
        loggedInUserIcon.click()
    }

    fun shouldHaveLoggedInUser(user: User) {
        log.info("Checking if user ${user.username} is logged in in $componentName")
        loggedInUserIcon.shouldHave(attribute("title", user.username.lowercase()))
    }
}