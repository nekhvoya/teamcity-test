package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By
import org.openqa.selenium.By.cssSelector
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

abstract class BaseSubmitForm(componentLocator: By, componentName: String): BaseComponent(componentLocator, componentName) {
    private val saveButton: SelenideElement = `$`(cssSelector("#saveButtons .submitButton"))

    companion object {
        val log: Logger = getLogger(BaseSubmitForm::class.java.simpleName)
    }

    fun save() {
        log.info("Clicking Save button on $componentName")
        saveButton.click()
    }
}