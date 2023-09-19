package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By.className
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class AdminSidebarComponent: BaseComponent(className("admin-sidebar"), "Admin Sidebar Component") {
    private val sidebar: SelenideElement = `$`(componentLocator)
    private val menuItem = { item: String -> sidebar.find("a[href*=$item]") }

    companion object {
        val log: Logger = getLogger(AdminSidebarComponent::class.java.simpleName)
    }

    fun selectMenuItem(item: String) {
        log.info("Clicking menu item $item on $componentName")
        menuItem(item).click()
    }
}