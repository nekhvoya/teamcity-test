package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.EDIT_VCS_ROOT_URL
import org.openqa.selenium.By.className
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class EditVcsRootPage: BasePage(className("editVcsRootPage"), "Edit Vcs Root Page", EDIT_VCS_ROOT_URL) {
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
        log.info("Selecting VCS type $vcsType on $pageName")
        vcsTypeDropdown.click()
        vcsTypeOption(vcsType).click()
    }

    fun setVcsName(name: String) {
        log.info("Typing VCS name $name on $pageName")
        nameInput.type(name)
    }

    fun setUrl(url: String) {
        log.info("Typing VCS url $url on $pageName")
        urlInput.type(url)
    }

    fun setBranch(branch: String) {
        log.info("Typing branch $branch on $pageName")
        branchInput.type(branch)
    }

    fun save() {
        log.info("Clicking Create button on $pageName")
        createButton.click()
    }
}