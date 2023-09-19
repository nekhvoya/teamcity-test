package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.data.VcsRoot
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class CreateProjectFromUrlForm: BaseSubmitForm(id("createFromUrlForm"), "Create Project From Url Form") {
    private val urlInput: SelenideElement = `$`(id("url"))
    private val proceedButton: SelenideElement = `$`("[name=createProjectFromUrl]")

    companion object {
        val log: Logger = getLogger(CreateProjectFromUrlForm::class.java.simpleName)
    }

    fun createProject(vcs: VcsRoot) {
        vcs.url?.let { typeUrl(it) } ?: throw IllegalStateException("VCS URL was not set")
        clickProceedButton()
    }

    fun typeUrl(url: String) {
        log.info("Typing $url in Username input on $componentName")
        urlInput.type(url)
    }

    fun clickProceedButton() {
        log.info("Clicking Proceed button on $componentName")
        proceedButton.click()
    }
}