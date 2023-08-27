package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_PROJECT_URL
import com.jetbrains.teamcity.ui.components.Header
import org.openqa.selenium.By.className
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditProjectPage: BasePage(className("editProjectPage"), "Edit Project Page", EDIT_PROJECT_URL) {
    private val projectCreatedMessage: SelenideElement = `$`(id("message_projectCreated"))
    private val projectUpdatedMessage: SelenideElement = `$`(id("message_projectUpdated"))
    private val saveButton: SelenideElement = `$`(".saveButtonsBlock [type=submit]")
    private val createBuildConfigurationButton: SelenideElement = `$`("[href*=createBuild]")

    val header = Header()

    companion object {
        val log: Logger = getLogger(EditProjectPage::class.java.simpleName)
    }

    fun save() {
        log.info("Clicking Save button")
        saveButton.click()
    }

    fun clickCreateBuild() {
        log.info("Clicking Create Build Configuration button")
        createBuildConfigurationButton.click()
    }

    fun shouldHaveProjectCreatedMessage() {
        log.info("Checking if project created message is visible")
        projectCreatedMessage.shouldBe(visible)
    }

    fun shouldHaveProjectUpdatedMessage() {
        log.info("Checking if project updated message is visible")
        projectUpdatedMessage.shouldBe(visible)
    }
}