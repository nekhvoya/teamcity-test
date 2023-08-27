package com.jetbrains.teamcity.ui

import com.jetbrains.teamcity.config.EnvConfig.Companion.editBuildUrl
import com.jetbrains.teamcity.ui.components.AdminSidebarComponent
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditBuildPage: BasePage(id("buildTypeSettingsContainerInner"), "Edit Build Page", editBuildUrl) {

    val adminSidebar = AdminSidebarComponent()

    companion object {
        val log: Logger = getLogger(EditBuildPage::class.java.simpleName)
    }
}