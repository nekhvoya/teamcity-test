package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Condition
import org.openqa.selenium.By
import com.codeborne.selenide.Selenide.`$`

abstract class BaseComponent(val componentLocator: By, val componentName: String) {

    fun shouldBeVisible() {
        `$`(componentLocator).shouldBe(Condition.visible)
    }
}