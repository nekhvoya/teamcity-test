package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_VCS_SETTINGS_URL
import com.jetbrains.teamcity.ui.components.AdminSidebarComponent
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditVcsSettingsPage: BasePage(id("editVcsSettingsForm"), "Edit Vcs Settings Page", EDIT_VCS_SETTINGS_URL) {
    private val vcsRootUpdatedMessage: SelenideElement = `$`(id("message_vcsRootsUpdated"))

    val sideBar = AdminSidebarComponent()

    companion object {
        val log: Logger = getLogger(EditProjectPage::class.java.simpleName)
    }

    fun shouldHaveVcsRootUpdatedMessage() {
        log.info("Checking if VCS root updated message is visible")
        vcsRootUpdatedMessage.shouldBe(visible)
    }
}