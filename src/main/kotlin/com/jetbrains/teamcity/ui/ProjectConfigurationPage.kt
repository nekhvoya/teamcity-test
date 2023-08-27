package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.projectConfigurationsUrl
import org.openqa.selenium.By.cssSelector
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class ProjectConfigurationPage: BasePage(cssSelector("[class*=ProjectPageHeader]"), "Project Configuration Page", projectConfigurationsUrl) {
    private val editProjectButton: SelenideElement = Selenide.`$`(cssSelector("[href*=editProject]"))

    companion object {
        val log: Logger = getLogger(BuildConfigurationPage::class.java.simpleName)
    }

    fun clickEdit() {
        log.info("Clicking Edit Project button")
        editProjectButton.click()
    }
}