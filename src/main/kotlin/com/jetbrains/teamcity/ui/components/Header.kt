package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.ui.EditProjectPage
import org.openqa.selenium.By.tagName
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Header: BaseComponent(tagName("header"), "Header") {
    private val header: SelenideElement = `$`(componentLocator)
    private val projectsButton: SelenideElement = header.`$`("[title=Projects]")

    companion object {
        val log: Logger = LoggerFactory.getLogger(Header::class.java.simpleName)
    }

    fun clickProjects() {
        EditProjectPage.log.info("Clicking Projects button")
        projectsButton.click()
    }
}