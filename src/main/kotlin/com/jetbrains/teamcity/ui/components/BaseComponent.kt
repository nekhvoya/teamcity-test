package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

abstract class BaseComponent(val componentLocator: By, val componentName: String) {

    companion object {
        val log: Logger = getLogger(BaseComponent::class.java.simpleName)
    }

    fun shouldBeVisible() {
        log.info("Checking if component $componentName is visible")
        `$`(componentLocator).shouldBe(Condition.visible)
    }
}