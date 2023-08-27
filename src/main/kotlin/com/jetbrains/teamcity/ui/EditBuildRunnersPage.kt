package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.data.BaseStep
import org.openqa.selenium.By.className
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EditBuildRunnersPage: BasePage(id("buildStepsContainer"), "Edit Build Runners Page", EnvConfig.editBuildRunnersUrl) {
    private val addBuildStepButton: SelenideElement = `$`("[href*=editRunType]")
    private val settingsUpdatedMessage: SelenideElement = `$`(id("unprocessed_buildRunnerSettingsUpdated"))
    private val buildStepRows: ElementsCollection = `$$`(className("editBuildStepRow"))

    companion object {
        val log: Logger = LoggerFactory.getLogger(EditBuildRunnersPage::class.java.simpleName)
    }

    fun clickAddBuildStep() {
        log.info("Clicking Edit Build button")
        addBuildStepButton.click()
    }

    fun shouldHaveSettingsUpdatedMessage() {
        log.info("Checking if settings updated message is visible")
        settingsUpdatedMessage.shouldBe(visible)
    }

    fun shouldListBuildStep(buildStep: BaseStep) {
        log.info("Checking if build step ${buildStep.stepName} is visible")
        buildStepRows.findBy(text(buildStep.stepName)).shouldBe(visible)
    }
}