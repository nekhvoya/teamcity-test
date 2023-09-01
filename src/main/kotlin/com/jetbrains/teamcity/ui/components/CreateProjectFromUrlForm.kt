package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.data.VcsRoot
import org.openqa.selenium.By.id
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class CreateProjectFromUrlForm: BaseBuildStepForm(id("createFromUrlForm"), "Create Project From Url Form") {
    private val urlInput: SelenideElement = `$`(id("url"))
    private val usernameInput: SelenideElement = `$`(id("username"))
    private val passwordInput: SelenideElement = `$`(id("password"))
    private val proceedButton: SelenideElement = `$`("[name=createProjectFromUrl]")

    companion object {
        val log: Logger = getLogger(CreateProjectFromUrlForm::class.java.simpleName)
    }

    fun createProject(vcsConfig: VcsRoot) {
        typeUrl(vcsConfig.url)
        vcsConfig.username?.let { typeUsername(it) }
        vcsConfig.password?.let { typePassword(it) }
        clickProceedButton()
    }

    fun typeUrl(url: String) {
        log.info("Typing $url in Username input on $componentName")
        urlInput.type(url)
    }

    fun typeUsername(username: String) {
        log.info("Typing $username in Username input on $componentName")
        usernameInput.type(username)
    }

    fun typePassword(password: String) {
        log.info("Typing password in Password input on $componentName")
        passwordInput.type(password)
    }

    fun clickProceedButton() {
        log.info("Clicking Proceed button on $componentName")
        proceedButton.click()
    }
}