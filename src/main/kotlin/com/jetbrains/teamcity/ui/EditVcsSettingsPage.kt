package com.jetbrains.teamcity.ui

import com.codeborne.selenide.CheckResult
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Driver
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_VCS_SETTINGS_URL
import com.jetbrains.teamcity.ui.components.AdminSidebarComponent
import org.openqa.selenium.By.className
import org.openqa.selenium.By.id
import org.openqa.selenium.WebElement
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.time.Duration.ofSeconds

class EditVcsSettingsPage: BasePage(id("editVcsSettingsForm"), "Edit Vcs Settings Page", EDIT_VCS_SETTINGS_URL) {
    private val vcsRootUpdatedMessage: SelenideElement = `$`(id("message_vcsRootsUpdated"))
    private val vcsDetailsLabel: SelenideElement = `$`(".vcsRootInstanceDetails")

    val sideBar = AdminSidebarComponent()

    companion object {
        val log: Logger = getLogger(EditProjectPage::class.java.simpleName)
    }

    fun shouldHaveVcsRootUpdatedMessage() {
        log.info("Checking if VCS root updated message is visible on $pageName")
        vcsRootUpdatedMessage.shouldBe(visible)
    }

    fun waitForCheckForChanges() {
        log.info("Waiting for check for changes on $pageName")
        vcsDetailsLabel.shouldHave(object: Condition("check for changes") {
            override fun check(driver: Driver, element: WebElement): CheckResult {
                refresh()
                val date: SelenideElement = vcsDetailsLabel.find(className("date"))
                return CheckResult(date.isDisplayed, date.text)
            }
        }, ofSeconds(15))
    }
}