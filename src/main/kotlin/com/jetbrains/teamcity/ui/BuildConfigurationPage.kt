package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.BUILD_CONFIGURATION_URL
import com.jetbrains.teamcity.ui.components.BuildLogForm
import com.jetbrains.teamcity.ui.components.BuildOverviewForm
import org.openqa.selenium.By.cssSelector
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.time.Duration

class BuildConfigurationPage: BasePage(cssSelector("[class*=BuildPageHeader]"), "Build Configuration Page", BUILD_CONFIGURATION_URL) {
    private val editConfigurationButton: SelenideElement = `$`(cssSelector("[href*=editBuild]"))
    private val overviewTab = { title: String -> `$`(cssSelector("[data-tab-title='${title}']")) }
    private val headerInfo: SelenideElement = `$`(cssSelector("[class*=headerInfo]"))

    val buildOverview = BuildOverviewForm()
    val buildLog = BuildLogForm()

    companion object {
        val log: Logger = getLogger(BuildConfigurationPage::class.java.simpleName)
    }

    fun clickEdit() {
        log.info("Clicking Edit Configuration button")
        editConfigurationButton.click()
    }

    fun openOverviewTab(tab: String) {
        log.info("Opening ${tab} tab")
        overviewTab(tab).click()
    }

    fun shouldDisplayResult(result: String, timeout: Duration) {
        log.info("Checking if build result is $result")
        headerInfo.shouldHave(text(result), timeout)
    }
}