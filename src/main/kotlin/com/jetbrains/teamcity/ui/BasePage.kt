package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.WebDriverRunner.getWebDriver
import org.openqa.selenium.By
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

abstract class BasePage(val pageLocator: By, val pageName: String, val pageUrl: String) {
    companion object {
        val log: Logger = getLogger(BasePage::class.java.simpleName)
    }

    fun open() {
        log.info("Opening $pageName by url $pageUrl")
        Selenide.open(pageUrl)
        getWebDriver().manage().window().maximize()
    }

    fun shouldBeOpened() {
        log.info("Checking if page $pageName is opened")
        `$`(pageLocator).shouldBe(visible)
    }

    fun refresh() {
        log.info("Refreshing $pageName")
        Selenide.refresh()
    }
}