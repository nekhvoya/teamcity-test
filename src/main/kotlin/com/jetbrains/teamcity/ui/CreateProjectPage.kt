package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.data.Project
import com.jetbrains.teamcity.utils.randomString
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CreateProjectPage: BasePage(id("editProjectForm"), "Create Project Page", EnvConfig.createProjectUrl) {
    private val nameInput: SelenideElement = `$`(id("name"))
    private val idInput: SelenideElement = `$`(id("externalId"))
    private val createButton: SelenideElement = `$`(id("createProject"))

    companion object {
        val log: Logger = LoggerFactory.getLogger(CreateProjectPage::class.java.simpleName)
    }

    fun createRandomProject(): Project {
        val name: String = randomString()
        typeName(name)
        val id: String = idInput.value
            .takeIf { it != null }
            ?: throw IllegalStateException("Unable to get id of created project")
        clickCreateButton()
        return Project(id, name)
    }

    fun typeName(name: String) {
        log.info("Typing $name in Name input on $pageName")
        nameInput.type(name)
    }

    fun clickCreateButton() {
        log.info("Clicking Create button on $pageName")
        createButton.click()
    }
}
