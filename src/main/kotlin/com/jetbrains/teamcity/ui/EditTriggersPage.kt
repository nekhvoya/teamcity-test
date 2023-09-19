package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_TRIGGERS_URL
import com.jetbrains.teamcity.ui.components.EditTriggerDialog
import com.jetbrains.teamcity.ui.components.Header
import com.jetbrains.teamcity.ui.components.UsersComponent
import org.openqa.selenium.By.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditTriggersPage: BasePage(xpath("//h2[contains(text(), 'Triggers')]"), "Edit Triggers Page", EDIT_TRIGGERS_URL) {
    private val addTriggerButton: SelenideElement = `$`(className("addNew"))
    private val triggersUpdateMessage: SelenideElement = `$`(id("unprocessed_buildTriggersUpdated"))

    val header = Header()
    val editTriggerDialog = EditTriggerDialog()

    companion object {
        val log: Logger = getLogger(EditTriggersPage::class.java.simpleName)
    }

    fun clickAddNewTrigger() {
        UsersComponent.log.info("Clicking Add New Trigger button on $pageName")
        addTriggerButton.click()
    }

    fun shouldDisplayTriggersUpdatedMessage() {
        log.info("Check if triggers updated message is displayed on $pageName")
        triggersUpdateMessage.shouldBe(Condition.visible)
    }
}