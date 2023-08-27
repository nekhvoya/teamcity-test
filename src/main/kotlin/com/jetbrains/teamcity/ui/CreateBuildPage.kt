package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.createBuildUrl
import com.jetbrains.teamcity.data.Build
import com.jetbrains.teamcity.utils.randomString
import org.openqa.selenium.By.id
import org.openqa.selenium.By.name
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class CreateBuildPage: BasePage(id("createBuildTypeForm"), "Create Build Page", createBuildUrl) {
    private val nameInput: SelenideElement = Selenide.`$`(id("buildTypeName"))
    private val idInput: SelenideElement = Selenide.`$`(id("buildTypeExternalId"))
    private val createButton: SelenideElement = Selenide.`$`(name("createBuildType"))

    companion object {
        val log: Logger = getLogger(CreateBuildPage::class.java.simpleName)
    }

    fun createRandomBuild(): Build {
        val name: String = randomString()
        typeName(name)
        val id: String = idInput.value
            .takeIf { it != null }
            ?: throw IllegalStateException("Unable to get id of created project")
        clickCreateButton()
        return Build(id, name)
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