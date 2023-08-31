package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig
import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.By.xpath
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.time.Duration.ofSeconds

class AgentsOverviewPage: BasePage(cssSelector("[class*=AgentsOverviewPage__page]"), "Agents Pool Page", EnvConfig.AGENTS_OVERVIEW_URL) {
    private val unauthorizedAgentsPanel: SelenideElement = `$`("[data-hint-container-id=unauthorized-agents]")
    private val unauthorizedAgentsCount: SelenideElement = unauthorizedAgentsPanel.find("[class*=ExpandablePanel__subheading]")
    private val expandUnauthorizedAgentsButton: SelenideElement = unauthorizedAgentsPanel.find("[title*='Expand section']")
    private val agentRow = { agentName: String -> `$`(xpath("//div[@data-test='agent' and contains(., '$agentName')]")) }
    private val agentTitle = { agentRow: SelenideElement -> agentRow.find("[title*='Agent name']") }

    companion object {
        val log: Logger = getLogger(AgentsOverviewPage::class.java.simpleName)
    }

    fun expandUnauthorizedAgents() {
        log.info("Expanding unauthorized agents on $pageName")
        if (expandUnauthorizedAgentsButton.isDisplayed) {
            expandUnauthorizedAgentsButton.click()
        }
    }

    fun clickAgent(agentName: String) {
        log.info("Clicking title of agent $agentName on $pageName")
        agentTitle(agentRow(agentName)).click()
    }

    fun shouldDisplayUnauthorizedAgents() {
        log.info("Checking that unauthorized agents are displayed on $pageName")
        unauthorizedAgentsCount.shouldBe(visible, ofSeconds(10))
    }

    fun shouldListAgent(agentName: String) {
        log.info("Checking that agent $agentName is listed on $pageName")
        agentRow(agentName).shouldBe(visible, ofSeconds(15))
    }
}