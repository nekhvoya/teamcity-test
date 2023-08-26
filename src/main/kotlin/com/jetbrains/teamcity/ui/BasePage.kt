package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class BasePage(val pageLocator: By, val pageName: String, val pageUrl: String) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(BasePage::class.java.simpleName)
    }

    fun open() {
        LoginPage.log.info("Opening $pageName by url $pageUrl")
        Selenide.open(pageUrl)
    }

    fun shouldBeOpened() {
        `$`(pageLocator).shouldBe(visible)
    }

    fun refresh() {
        log.info("Refreshing $pageName")
        Selenide.refresh()
    }
}