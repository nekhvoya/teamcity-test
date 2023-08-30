package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_VCS_ROOT_URL
import org.openqa.selenium.By.className
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditVcsRootPage: BasePage(className("editVcsRootPage"), "Edit Vcs Root Page", EDIT_VCS_ROOT_URL) {
    private val projectCreatedMessage: SelenideElement = `$`(id("unprocessed_buildTypeCreated"))
    private val vcsTypeDropdown: SelenideElement = `$`(id("-ufd-teamcity-ui-vcsName"))
    private val vcsTypeOption = { option: String -> `$`("li[data-title=${option}]") }
    private val nameInput = `$`(id("vcsRootName"))
    private val urlInput = `$`(id("url"))
    private val branchInput = `$`(id("branch"))
    private val createButton = `$`(".saveButtonsBlock .submitButton")

    companion object {
        val log: Logger = getLogger(EditVcsRootPage::class.java.simpleName)
    }

    fun selectVcsType(vcsType: String) {
        log.info("Selecting VCS type $vcsType")
        vcsTypeDropdown.click()
        vcsTypeOption(vcsType).click()
    }

    fun setVcsName(name: String) {
        log.info("Typing VCS name $name")
        nameInput.type(name)
    }

    fun setUrl(url: String) {
        log.info("Typing VCS url $url")
        urlInput.type(url)
    }

    fun setBranch(branch: String) {
        log.info("Typing branch $branch")
        branchInput.type(branch)
    }

    fun save() {
        log.info("Clicking Create button")
        createButton.click()
    }

    fun shouldHaveBuildConfigurationCreatedMessage() {
        log.info("Checking if build created message is visible")
        projectCreatedMessage.shouldBe(Condition.visible)
    }
}