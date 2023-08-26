package com.jetbrains.teamcity.utils

import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebDriverRunner
import org.openqa.selenium.JavascriptExecutor

fun setAttribute(element: SelenideElement, attribute: String, value: String) {
    (WebDriverRunner.getWebDriver() as JavascriptExecutor)
        .executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attribute, value)
}