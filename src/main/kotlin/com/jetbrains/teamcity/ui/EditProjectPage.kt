package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.ui.components.Header
import org.openqa.selenium.By.className
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EditProjectPage: BasePage(className("editProjectPage"), "Edit Project Page", EnvConfig.editProjectUrl) {
    private val projectCreatedMessage: SelenideElement = `$`(id("message_projectCreated"))
    private val projectUpdatedMessage: SelenideElement = `$`(id("message_projectUpdated"))
    private val saveButton: SelenideElement = `$`(".saveButtonsBlock [type=submit]")

    val header = Header()

    companion object {
        val log: Logger = LoggerFactory.getLogger(EditProjectPage::class.java.simpleName)
    }

    fun save() {
        log.info("Clicking Save button")
        saveButton.click()
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