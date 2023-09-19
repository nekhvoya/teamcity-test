package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.PROJECTS_URL
import com.jetbrains.teamcity.data.Build
import com.jetbrains.teamcity.data.Project
import com.jetbrains.teamcity.ui.components.Header
import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.By.xpath
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.time.Duration

class ProjectsPage: BasePage(cssSelector("[class*=ProjectPageHeader]"), "Projects Page", PROJECTS_URL) {
    private val projectContainer = { projectName: String ->
        `$`(xpath("//div[contains(@class, 'Subproject__container') and contains(., '$projectName')]")) }
    private val newProjectButton: SelenideElement =
        `$`("[data-hint-container-id=project-create-entity]")
    private val projectDetailsButton = { projectContainer: SelenideElement ->
        projectContainer.find("[class*=Details__button]") }
    private val projectArrow = { projectContainer: SelenideElement ->
        projectContainer.find("[class*=Subproject__arrow]") }
    private val buildContainer = { buildName: String ->
        `$`(xpath("//div[contains(@class, 'BuildsByBuildType__container') and contains(., '$buildName')]")) }
    private val runButton = { buildContainer: SelenideElement ->
        `$`("[data-test=run-build]")}
    private val successLabel = { buildContainer: SelenideElement ->
        `$`("[data-test-link-with-icon=finished_green]")}
    private val agentLink = { buildContainer: SelenideElement ->
        `$`("[class*=AgentLink]")}

    val header = Header()

    companion object {
        val log: Logger = getLogger(ProjectsPage::class.java.simpleName)
    }

    fun createNewProject() {
        log.info("Clicking New Project button on $pageName")
        newProjectButton.click()
    }

    fun expandProject(project: Project) {
        log.info("Expanding project $project on $pageName")
        if (isProjectExpanded(project)) {
            log.warn("Project ${project.name} is already expanded")
            return
        }
        projectArrow(projectContainer(project.name)).click()
    }

    fun runBuild(build: Build) {
        log.info("Clicking run button of build ${build.name} on $pageName")
        runButton(buildContainer(build.name)).click()
    }

    fun isProjectExpanded(project: Project): Boolean {
        log.info("Checking if project $project is expanded on $pageName")
        return projectDetailsButton(projectContainer(project.name)).getAttribute("aria-expanded").toBoolean()
    }

    fun shouldListBuildForProject(project: Project, build: Build) {
        log.info("Checking if build ${build.name} is listed on $pageName")
        projectContainer(project.name).find("[href*=buildConfiguration][title=${build.name}]").shouldBe(visible)
    }

    fun shouldListSuccessfulBuild(build: Build, timeout: Duration) {
        log.info("Checking if build ${build.name} completed successfully on $pageName")
        successLabel(buildContainer(build.name)).shouldBe(visible, timeout)
    }

    fun shouldListAgentForBuild(build: Build, agentName: String) {
        log.info("Checking id build ${build.name} was run on agent $agentName on $pageName")
        agentLink(buildContainer(build.name)).shouldHave(text(agentName))
    }
}