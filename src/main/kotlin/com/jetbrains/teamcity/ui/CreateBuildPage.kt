package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.data.Build
import org.apache.commons.lang3.RandomStringUtils
import org.openqa.selenium.By.id
import org.openqa.selenium.By.name
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CreateBuildPage: BasePage(id("createBuildTypeForm"), "Create Build Page", EnvConfig.createBuildUrl) {
    private val nameInput: SelenideElement = Selenide.`$`(id("buildTypeName"))
    private val idInput: SelenideElement = Selenide.`$`(id("buildTypeExternalId"))
    private val createButton: SelenideElement = Selenide.`$`(name("createBuildType"))

    companion object {
        val log: Logger = LoggerFactory.getLogger(CreateBuildPage::class.java.simpleName)
    }

    fun createRandomBuild(): Build {
        val name: String = RandomStringUtils.randomAlphabetic(10)
        typeName(name)
        val id: String = idInput.value
            .takeIf { it != null }
            ?: throw IllegalStateException("Unable to get id of created project")
        clickCreateButton()
        return Build(id, name)
    }

    fun typeName(name: String) {
        CreateProjectPage.log.info("Typing $name in Name input on $pageName")
        nameInput.type(name)
    }

    fun clickCreateButton() {
        CreateProjectPage.log.info("Clicking Create button on $pageName")
        createButton.click()
    }
}