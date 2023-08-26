package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig
import org.openqa.selenium.By.cssSelector
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BuildConfigurationPage: BasePage(cssSelector("[class*=BuildTypePageHeader]"), "Build Configuration Page", EnvConfig.buildConfigurationsUrl) {
    private val editConfigurationButton: SelenideElement = Selenide.`$`(cssSelector("[href*=editBuild]"))

    companion object {
        val log: Logger = LoggerFactory.getLogger(BuildConfigurationPage::class.java.simpleName)
    }

    fun clickEdit() {
        log.info("Clicking Edit Configuration button")
        editConfigurationButton.click()
    }
}