package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditTriggerDialog: BaseComponent(id("editTriggerDialog"), "Edit Trigger Dialog") {
    private val triggerSelect: SelenideElement = `$`(id("triggerNameSelector"))
    private val saveButton: SelenideElement = `$`(id("editTriggerSubmit"))

    companion object {
        val log: Logger = getLogger(EditTriggerDialog::class.java.simpleName)
    }

    fun selectTrigger(trigger: String) {
        log.info("Selecting trigger $trigger in $componentName")
        triggerSelect.selectOption(trigger)
    }

    fun clickSave() {
        EditRequirementDialog.log.info("Clicking Save button in $componentName")
        saveButton.click()
    }
}