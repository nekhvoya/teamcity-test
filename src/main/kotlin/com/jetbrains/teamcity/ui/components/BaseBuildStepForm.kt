package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By
import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

abstract class BaseBuildStepForm(componentLocator: By, componentName: String): BaseComponent(componentLocator, componentName) {
    private val buildStepNameInput: SelenideElement = `$`(id("buildStepName"))
    private val saveButton: SelenideElement = `$`(cssSelector("#saveButtons .submitButton"))

    companion object {
        val log: Logger = getLogger(BaseBuildStepForm::class.java.simpleName)
    }

    fun typeStepName(stepName: String) {
        log.info("Typing step name $stepName")
        buildStepNameInput.type(stepName)
    }

    fun save() {
        log.info("Clicking Save button")
        saveButton.click()
    }
}