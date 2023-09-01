package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.CREATE_PROJECT_URL
import com.jetbrains.teamcity.ui.components.CreateProjectForm
import com.jetbrains.teamcity.ui.components.CreateProjectFromUrlForm
import org.openqa.selenium.By.className
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class CreateProjectPage: BasePage(className("createFormContainer"), "Create Project Page", CREATE_PROJECT_URL) {
    private val createManuallyButton: SelenideElement = `$`("[data-hint-container-id=create-project]")
    private val createFromUrlButton: SelenideElement = `$`("[href*=createFromUrl]")

    val createProjectForm = CreateProjectForm()
    val createProjectFromUrlForm = CreateProjectFromUrlForm()

    companion object {
        val log: Logger = getLogger(CreateProjectPage::class.java.simpleName)
    }

    fun clickCreateManuallyButton() {
        log.info("Clicking Create Manually button on $pageName")
        createManuallyButton.click()
    }

    fun clickCreateFromUrlButton() {
        log.info("Clicking Create From Url button on $pageName")
        createFromUrlButton.click()
    }
}
