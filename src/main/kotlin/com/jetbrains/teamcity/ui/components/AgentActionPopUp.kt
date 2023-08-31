package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By.cssSelector
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class AgentActionPopUp: BaseComponent(cssSelector("[data-test=ring-popup]"), "Agent Action PopUp") {
    private val submitButton: SelenideElement = `$`("button[type=submit]")

    companion object {
        val log: Logger = getLogger(AgentActionPopUp::class.java.simpleName)
    }

    fun submitAction() {
        log.info("Clicking submit button in $componentName")
        submitButton.click()
    }
}