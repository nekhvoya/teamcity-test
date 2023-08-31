package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_REQUIREMENTS_URL
import com.jetbrains.teamcity.ui.components.EditRequirementDialog
import org.openqa.selenium.By.className
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditRequirementsPage: BasePage(className("agentsCompatibilityTable"), "Edit Requirements Page", EDIT_REQUIREMENTS_URL) {
    private val addRequirementButton: SelenideElement = `$`(className("addNew"))
    private val requirementsUpdateMessage: SelenideElement = `$`(id("unprocessed_requirementsUpdated"))

    val editRequirementDialog = EditRequirementDialog()

    companion object {
        val log: Logger = getLogger(EditRequirementsPage::class.java.simpleName)
    }

    fun clickAddNewRequirement() {
        log.info("Clicking Add New Requirement button on $pageName")
        addRequirementButton.click()
    }

    fun shouldDisplayRequirementsUpdatedMessage() {
        log.info("Check if requirements updated message is displayed on $pageName")
        requirementsUpdateMessage.shouldBe(visible)
    }
}