package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.buildConfigurationsUrl
import org.openqa.selenium.By.cssSelector
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class BuildConfigurationPage: BasePage(cssSelector("[class*=BuildTypePageHeader]"), "Build Configuration Page", buildConfigurationsUrl) {
    private val editConfigurationButton: SelenideElement = Selenide.`$`(cssSelector("[href*=editBuild]"))

    companion object {
        val log: Logger = getLogger(BuildConfigurationPage::class.java.simpleName)
    }

    fun clickEdit() {
        log.info("Clicking Edit Configuration button")
        editConfigurationButton.click()
    }
}