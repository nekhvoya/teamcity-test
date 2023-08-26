package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig
import org.openqa.selenium.By.className
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EditVcsRootPage: BasePage(className("editVcsRootPage"), "Edit Vcs Root Page", EnvConfig.editVcsRootUrl) {
    private val projectCreatedMessage: SelenideElement = `$`(id("unprocessed_buildTypeCreated"))

    companion object {
        val log: Logger = LoggerFactory.getLogger(EditVcsRootPage::class.java.simpleName)
    }

    fun shouldHaveBuildConfigurationCreatedMessage() {
        log.info("Checking if build created message is visible")
        projectCreatedMessage.shouldBe(Condition.visible)
    }
}