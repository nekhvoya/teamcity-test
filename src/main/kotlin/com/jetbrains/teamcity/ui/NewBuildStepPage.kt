package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide.`$`
import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_RUN_TYPE_URL
import com.jetbrains.teamcity.ui.components.PowerShellForm
import org.openqa.selenium.By.id
import org.openqa.selenium.By.xpath
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class NewBuildStepPage: BasePage(id("select-runner-flatten"), "New Build Step Page", EDIT_RUN_TYPE_URL) {
    private val runnerItem = { itemName: String -> `$`(xpath("//tr[contains(@data-test, 'runner-item') and contains(., '$itemName')]")) }

    val powerShellForm = PowerShellForm()

    companion object {
        val log: Logger = getLogger(EditProjectPage::class.java.simpleName)
    }

    fun selectRunner(runner: String) {
        log.info("Selecting runner $runner")
        runnerItem(runner).click()
    }
}