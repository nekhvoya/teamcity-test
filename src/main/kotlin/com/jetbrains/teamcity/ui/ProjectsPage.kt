package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.attribute
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig.Companion.PROJECTS_URL
import com.jetbrains.teamcity.data.Build
import com.jetbrains.teamcity.data.Project
import com.jetbrains.teamcity.data.User
import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.By.xpath
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import java.time.Duration

class ProjectsPage: BasePage(cssSelector("[class*=ProjectPageHeader]"), "Projects Page", PROJECTS_URL) {
    private val loggedInUserIcon: SelenideElement = `$`("[data-hint-container-id=header-user-menu] button")
    private val projectContainer = { projectName: String ->
        `$`(xpath("//div[contains(@class, 'Subproject__container') and contains(., '$projectName')]")) }
    private val newProjectButton: SelenideElement =
        `$`("[data-hint-container-id=project-create-entity]")
    private val projectDetailsButton = { projectContainer: SelenideElement ->
        projectContainer.find("[class*=Details__button]") }
    private val projectArrow = { projectContainer: SelenideElement ->
        projectContainer.find("[class*=Subproject__arrow]") }
    private val projectLink = { projectContainer: SelenideElement ->
        projectContainer.find("[href*=project]") }
    private val buildLink = { projectContainer: SelenideElement, buildName: String ->
        projectContainer.find("[href*=buildConfiguration][title=${buildName}]") }
    private val buildContainer = { buildName: String ->
        `$`(xpath("//div[contains(@class, 'BuildsByBuildType__container') and contains(., '$buildName')]")) }
    private val runButton = { buildContainer: SelenideElement ->
        `$`("[data-test=run-build]")}
    private val successLabel = { buildContainer: SelenideElement ->
        `$`("[data-test-link-with-icon=finished_green]")}

    companion object {
        val log: Logger = getLogger(ProjectsPage::class.java.simpleName)
    }

    fun createNewProject() {
        log.info("Clicking New Project button")
        newProjectButton.click()
    }

    fun clickProject(project: Project) {
        log.info("Clicking build link ${project.name}")
        projectLink(projectContainer(project.name)).click()
    }

    fun clickBuildFromProject(project: Project, build: Build) {
        log.info("Clicking build link ${build.name}")
        buildLink(projectContainer(project.name), build.name).click()
    }

    fun expandProject(project: Project) {
        log.info("Expanding project $project")
        if (isProjectExpanded(project)) {
            log.warn("Project ${project.name} is already expanded")
            return
        }
        projectArrow(projectContainer(project.name)).click()
    }

    fun runBuild(build: Build) {
        log.info("Clicking run button of build ${build.name}")
        runButton(buildContainer(build.name)).click()
    }

    fun isProjectExpanded(project: Project): Boolean {
        log.info("Checking if project $project is expanded")
        return projectDetailsButton(projectContainer(project.name)).getAttribute("aria-expanded").toBoolean()
    }

    fun shouldHaveLoggedInUser(user: User) {
        log.info("Checking if user ${user.username} is logged in")
        loggedInUserIcon.shouldHave(attribute("title", user.username))
    }

    fun shouldListProject(project: Project) {
        log.info("Checking if project ${project.name} is listed")
        projectLink(projectContainer(project.name)).shouldHave(attribute("title", project.name))
    }

    fun shouldListBuildForProject(project: Project, build: Build) {
        log.info("Checking if build ${build.name} is listed")
        projectContainer(project.name).find("[href*=buildConfiguration][title=${build.name}]").shouldBe(visible)
    }

    fun shouldListSuccessfulBuild(build: Build, timeout: Duration) {
        log.info("Checking if build ${build.name} completed successfully")
        successLabel(buildContainer(build.name)).shouldBe(visible, timeout)
    }
}