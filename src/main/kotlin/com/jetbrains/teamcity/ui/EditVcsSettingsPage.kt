package com.jetbrains.teamcity.ui

import com.jetbrains.teamcity.config.EnvConfig.Companion.editVcsSettingsUrl
import com.jetbrains.teamcity.ui.components.Header
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditVcsSettingsPage: BasePage(id("editVcsSettingsForm"), "Edit Vcs Settings Page", editVcsSettingsUrl) {
    val header = Header()

    companion object {
        val log: Logger = getLogger(EditProjectPage::class.java.simpleName)
    }
}