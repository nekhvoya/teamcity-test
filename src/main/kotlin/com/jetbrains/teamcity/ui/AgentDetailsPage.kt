package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.interactable
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.ui.components.AgentActionPopUp
import com.jetbrains.teamcity.ui.components.Header
import org.openqa.selenium.By
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.time.Duration.ofSeconds

class AgentDetailsPage: BasePage(By.cssSelector("[class*=AgentPage__page]"), "Agent Details Page", EnvConfig.AGENTS_DETAILS_URL) {
    private val connectionStatusLabel: SelenideElement = `$`("[data-test-agent-connection-status]")
    private val authorizationStatusLabel: SelenideElement = `$`("[data-agent-authorization-status]")
    private val authorizeButton: SelenideElement = `$`("[data-test-authorize-agent=true]")

    val agentActionPopUp = AgentActionPopUp()
    val header = Header()

    companion object {
        val log: Logger = getLogger(AgentDetailsPage::class.java.simpleName)
    }

    fun authorizeAgent() {
        log.info("Clicking Authorize button on $pageName")
        authorizeButton.shouldBe(interactable, ofSeconds(10)).click()
    }

    fun shouldDisplayConnectionStatus(status: String) {
        log.info("Checking if agent has connection status $status on $pageName")
        // Waiting for agent to connect
        connectionStatusLabel.shouldHave(text(status), ofSeconds(30))
    }

    fun shouldDisplayAuthorizationStatus(status: String) {
        log.info("Checking if agent has authorization status $status on $pageName")
        authorizationStatusLabel.shouldHave(text(status), ofSeconds(10))
    }
}