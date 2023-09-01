package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.checked
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.PROJECT_SETUP_URL
import com.jetbrains.teamcity.data.Project
import org.openqa.selenium.By.id
import org.openqa.selenium.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.time.Duration.ofSeconds

class ProjectSetupPage: BasePage(id("createProjectForm"), "Project Setup Page", PROJECT_SETUP_URL) {
    private val projectNameInput: SelenideElement = `$`(id("projectName"))
    private val proceedButton: SelenideElement = `$`("[value=Proceed]")
    private val savingIcon: SelenideElement = `$`(id("saving"))
    private val importSettingsRadioButton: SelenideElement = `$`(id("vcsSettings_LOAD_WITHOUT_SYNC"))

    companion object {
        val log: Logger = getLogger(ProjectSetupPage::class.java.simpleName)
    }

    fun clickProceed() {
        log.info("Clicking Proceed button on $pageName")
        proceedButton.click()
    }

    fun waitSaving() {
        log.info("Waiting for saving on $pageName")
        savingIcon.shouldBe(visible)
        savingIcon.shouldNotBe(visible, ofSeconds(60))
    }

    fun changeProjectName(project: Project) {
        log.info("Retyping project name ${project.name} on $pageName")
        projectNameInput.clear()
        projectNameInput.type(project.name)
    }

    fun shouldHaveSelectedImportSettingsRadioButton() {
        log.info("Checking if import settings from VCS radio button is selected on $pageName")
        importSettingsRadioButton.shouldBe(checked)
    }
}