package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.By.xpath
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class PopUpMenu: BaseComponent(cssSelector("[data-test=ring-popup]"), "Pop Up Menu") {
    private val menuItem = { item: String -> `$`(xpath("//a[contains(@class, 'ring-list-item') and contains(., '$item')]")) }

    companion object {
        val log: Logger = getLogger(PopUpMenu::class.java.simpleName)
    }

    fun selectItem(item: String) {
        log.info("Selecting item $item on $componentName")
        menuItem(item).click()
    }
}