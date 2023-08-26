package com.jetbrains.teamcity.ui

import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.ui.components.Header
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EditVcsSettingsPage: BasePage(id("editVcsSettingsForm"), "Edit Vcs Settings Page", EnvConfig.editVcsSettingsUrl) {
    val header = Header()

    companion object {
        val log: Logger = LoggerFactory.getLogger(EditProjectPage::class.java.simpleName)
    }
}