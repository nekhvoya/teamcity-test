package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.ui.BuildConfigurationPage
import org.openqa.selenium.By

class BuildLogComponent: BaseComponent(By.cssSelector("[aria-label='Build Log']"), "Build Log Form") {
    private val logLine = { logText: String -> Selenide.`$`(By.xpath("//div[contains(@class, 'LogMessageFocusWrapper') and contains(., '$logText')]")) }
    private val expandLogButton = { logLine: SelenideElement -> logLine.find(By.cssSelector("[title=Expand]")) }

    fun openStepLog(logText: String) {
        BuildConfigurationPage.log.info("Checking if build overview is visible on $componentName")
        expandLogButton(logLine(logText)).click()
    }

    fun shouldDisplayOutPutLog(logText: String) {
        BuildConfigurationPage.log.info("Checking if build overview is visible on $componentName")
        logLine(logText).shouldBe(Condition.visible)
    }
}