package com.jetbrains.teamcity.ui

import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.ui.components.AdminSidebarComponent
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EditBuildPage: BasePage(id("buildTypeSettingsContainerInner"), "Edit Build Page", EnvConfig.editBuildUrl) {

    val adminSidebar = AdminSidebarComponent()

    companion object {
        val log: Logger = LoggerFactory.getLogger(EditBuildPage::class.java.simpleName)
    }
}