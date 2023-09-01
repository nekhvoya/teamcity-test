package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.data.Project
import com.jetbrains.teamcity.utils.randomString
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class CreateProjectForm: BaseBuildStepForm(id("editProjectForm"), "Create Project Form") {
    private val nameInput: SelenideElement = `$`(id("name"))
    private val idInput: SelenideElement = `$`(id("externalId"))
    private val createButton: SelenideElement = `$`(id("createProject"))

    companion object {
        val log: Logger = getLogger(CreateProjectForm::class.java.simpleName)
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
        log.info("Typing $name in Name input on $componentName")
        nameInput.type(name)
    }

    fun clickCreateButton() {
        log.info("Clicking Create button on $componentName")
        createButton.click()
    }
}