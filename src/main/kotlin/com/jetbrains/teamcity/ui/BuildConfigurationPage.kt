package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.BUILD_CONFIGURATION_URL
import com.jetbrains.teamcity.ui.components.BuildLogComponent
import com.jetbrains.teamcity.ui.components.BuildOverviewComponent
import org.openqa.selenium.By.cssSelector
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.time.Duration

class BuildConfigurationPage: BasePage(cssSelector("[class*=BuildPageHeader]"), "Build Configuration Page", BUILD_CONFIGURATION_URL) {
    private val overviewTab = { title: String -> `$`("[data-tab-title='${title}']") }
    private val headerInfo: SelenideElement = `$`("[class*=headerInfo]")

    val buildOverview = BuildOverviewComponent()
    val buildLog = BuildLogComponent()

    companion object {
        val log: Logger = getLogger(BuildConfigurationPage::class.java.simpleName)
    }

    fun openOverviewTab(tab: String) {
        log.info("Opening ${tab} tab on $pageName")
        overviewTab(tab).click()
    }

    fun shouldDisplayResult(result: String, timeout: Duration) {
        log.info("Checking if build result is $result on $pageName")
        headerInfo.shouldHave(text(result), timeout)
    }
}