package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide.`$`
import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.constants.Runner
import com.jetbrains.teamcity.ui.components.CommandLineForm
import org.openqa.selenium.By.id
import org.openqa.selenium.By.xpath
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class NewBuildStepPage: BasePage(id("select-runner-flatten"), "New Build Step Page", EnvConfig.editRunTypeUrl) {
    private val runnerItem = { itemName: String -> `$`(xpath("//tr[contains(@data-test, 'runner-item') and contains(., '$itemName')]")) }

    val commandLineForm = CommandLineForm()

    companion object {
        val log: Logger = LoggerFactory.getLogger(EditProjectPage::class.java.simpleName)
    }

    fun selectRunner(cmd: Runner) {
        log.info("Selecting runner $cmd")
        runnerItem(cmd.runnerName).click()
    }
}