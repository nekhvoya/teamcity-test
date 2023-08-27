package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By.className
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class AdminSidebarComponent: BaseComponent(className("admin-sidebar"), "Admin Sidebar Component") {
    private val buildStepsMenuItem: SelenideElement = `$`("[href*=editBuildRunners]")

    companion object {
        val log: Logger = getLogger(AdminSidebarComponent::class.java.simpleName)
    }

    fun selectBuildSteps() {
        log.info("Clicking Build Steps menu item")
        buildStepsMenuItem.click()
    }
}