package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_BUILD_URL
import com.jetbrains.teamcity.ui.components.AdminSidebarComponent
import org.openqa.selenium.By.id
import org.openqa.selenium.By.xpath
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditBuildPage: BasePage(id("buildTypeSettingsContainerInner"), "Edit Build Page", EDIT_BUILD_URL) {
    private val runButton: SelenideElement = `$`(xpath("//button[contains(text(), 'Run')]"))

    val sidebar = AdminSidebarComponent()

    companion object {
        val log: Logger = getLogger(EditBuildPage::class.java.simpleName)
    }

    fun clickRun() {
        log.info("Clicking Run button on $pageName")
        runButton.click()
    }
}