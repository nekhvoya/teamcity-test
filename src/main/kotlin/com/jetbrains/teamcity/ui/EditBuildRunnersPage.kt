package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_BUILD_RUNNER_URL
import com.jetbrains.teamcity.ui.components.BreadcrumbsComponent
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditBuildRunnersPage: BasePage(id("buildStepsContainer"), "Edit Build Runners Page", EDIT_BUILD_RUNNER_URL) {
    private val addBuildStepButton: SelenideElement = `$`("[href*=editRunType]")
    private val settingsUpdatedMessage: SelenideElement = `$`(id("unprocessed_buildRunnerSettingsUpdated"))

    val breadcrumbs = BreadcrumbsComponent()

    companion object {
        val log: Logger = getLogger(EditBuildRunnersPage::class.java.simpleName)
    }

    fun clickAddBuildStep() {
        log.info("Clicking Edit Build button on $pageName")
        addBuildStepButton.click()
    }

    fun shouldHaveSettingsUpdatedMessage() {
        log.info("Checking if settings updated message is visible on $pageName")
        settingsUpdatedMessage.shouldBe(visible)
    }
}