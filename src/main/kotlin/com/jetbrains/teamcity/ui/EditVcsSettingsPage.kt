package com.jetbrains.teamcity.ui

import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_VCS_SETTINGS_URL
import com.jetbrains.teamcity.ui.components.Header
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditVcsSettingsPage: BasePage(id("editVcsSettingsForm"), "Edit Vcs Settings Page", EDIT_VCS_SETTINGS_URL) {
    val header = Header()

    companion object {
        val log: Logger = getLogger(EditProjectPage::class.java.simpleName)
    }
}