package com.jetbrains.teamcity.ui

import com.jetbrains.teamcity.config.EnvConfig.Companion.ADMINISTRATION_URL
import com.jetbrains.teamcity.ui.components.AdminSidebarComponent
import com.jetbrains.teamcity.ui.components.Header
import com.jetbrains.teamcity.ui.components.ProjectsComponent
import com.jetbrains.teamcity.ui.components.UsersComponent
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class AdministrationPage: BasePage(id("admin-container"), "Administration Page", ADMINISTRATION_URL) {
    val sidebar = AdminSidebarComponent()
    val usersForm = UsersComponent()
    val projectsForm = ProjectsComponent()
    val header = Header()

    companion object {
        val log: Logger = getLogger(AdministrationPage::class.java.simpleName)
    }
}