package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_PROJECT_URL
import com.jetbrains.teamcity.ui.components.Header
import org.openqa.selenium.By.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditProjectPage: BasePage(className("editProjectPage"), "Edit Project Page", EDIT_PROJECT_URL) {
    private val projectCreatedMessage: SelenideElement = `$`(id("message_projectCreated"))
    private val projectCreatedFromVcsMessage: SelenideElement = `$`(id("message_objectsCreated"))
    private val createBuildConfigurationButton: SelenideElement = `$`("[href*=createBuild]")
    private val buildConfigurationNameCell =
        { name: String -> `$`(id("configurations")).find(xpath(".//td[contains(@class, 'name') and contains(., '$name')]")) }
    private val buildRunnerCell =
        { runner: String -> `$`(id("configurations")).find(xpath(".//td[contains(@class, 'runner') and contains(., '$runner')]")) }

    val header = Header()

    companion object {
        val log: Logger = getLogger(EditProjectPage::class.java.simpleName)
    }

    fun clickCreateBuild() {
        log.info("Clicking Create Build Configuration button on $pageName")
        createBuildConfigurationButton.click()
    }

    fun openBuildConfiguration(buildName: String) {
        log.info("Clicking build configuration name cell on $pageName")
        buildConfigurationNameCell(buildName).click()
    }

    fun shouldHaveProjectCreatedMessage() {
        log.info("Checking if project created message is visible on $pageName")
        projectCreatedMessage.shouldBe(visible)
    }

    fun shouldHaveProjectCreatedFromVcsMessage() {
        log.info("Checking if project created from VCS message is visible on $pageName")
        projectCreatedFromVcsMessage.shouldBe(visible)
    }

    fun shouldListBuildConfiguration(buildName: String) {
        log.info("Checking if build configuration is listed on $pageName")
        buildConfigurationNameCell(buildName).shouldBe(visible)
    }

    fun shouldListBuildStep(runnerName: String) {
        log.info("Checking if build step is listed on $pageName")
        buildRunnerCell(runnerName).shouldBe(visible)
    }
}