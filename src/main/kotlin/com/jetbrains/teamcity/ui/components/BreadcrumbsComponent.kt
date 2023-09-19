package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class BreadcrumbsComponent: BaseComponent(id("breadcrumbsWrapper"), "Bread Crumbs Component") {
    private val runFirstBuildButton: SelenideElement = `$`(".runFirstBuild")

    companion object {
        val log: Logger = getLogger(BreadcrumbsComponent::class.java.simpleName)
    }

    fun runFirstBuild() {
        log.info("Clicking Run First Build on $componentName")
        runFirstBuildButton.click()
    }
}