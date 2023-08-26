package com.jetbrains.teamcity.ui

import com.codeborne.selenide.Condition.attribute
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.config.UserCredentials
import com.jetbrains.teamcity.data.Build
import com.jetbrains.teamcity.data.Project
import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.By.xpath
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ProjectsPage: BasePage(cssSelector("[class*=ProjectPageHeader]"), "Projects Page", EnvConfig.projectsUrl) {
    private val loggedInUserIcon: SelenideElement = `$`("[data-hint-container-id=header-user-menu] button")
    private val projectContainer = { projectName: String ->`$`(xpath("//div[contains(@class, 'Subproject__container') and contains(., '$projectName')]")) }
    private val newConfigurationButton: SelenideElement = `$`(xpath("//span[contains(text(), 'New Configuration')]"))
    private val newProjectButton: SelenideElement = `$`("[data-hint-container-id=project-create-entity]")
    private val addBuildConfigurationButton = { projectContainer: SelenideElement -> projectContainer.find("[class*=addBuildConfiguration]") }
    private val projectDetailsButton = { projectContainer: SelenideElement -> projectContainer.find("[class*=Details__button]") }
    private val projectArrow = { projectContainer: SelenideElement -> projectContainer.find("[class*=Subproject__arrow]") }
    private val projectLink = { projectContainer: SelenideElement, projectName: String -> projectContainer.find("[href*=project][title=${projectName}]") }
    private val buildLink = { projectContainer: SelenideElement, buildName: String -> projectContainer.find("[href*=buildConfiguration][title=${buildName}]") }

    companion object {
        val log: Logger = LoggerFactory.getLogger(ProjectsPage::class.java.simpleName)
    }

    fun createNewProject() {
        log.info("Clicking New Project button")
        newProjectButton.click()
    }

    fun clickBuildFromProject(project: Project, build: Build) {
        log.info("Clicking build link ${build.name}")
        buildLink(projectContainer(project.name), build.name).click()
    }

    fun addBuildConfiguration(project: Project) {
        log.info("Clicking Add Build Configuration button of project ${project.name}")
        addBuildConfigurationButton(projectContainer(project.name)).click()
        newConfigurationButton.click()
    }

    fun expandProject(project: Project) {
        log.info("Expanding project $project")
        if (isProjectExpanded(project)) {
            log.warn("Project ${project.name} is already expanded")
            return
        }
        projectArrow(projectContainer(project.name)).click()
    }

    fun isProjectExpanded(project: Project): Boolean {
        log.info("Checking if project $project is expanded")
        return projectDetailsButton(projectContainer(project.name)).getAttribute("aria-expanded").toBoolean()
    }

    fun shouldHaveLoggedInUser(user: UserCredentials) {
        log.info("Checking if user ${user.username} is logged in")
        loggedInUserIcon.shouldHave(attribute("title", user.username))
    }

    fun shouldListProject(project: Project) {
        log.info("Checking if project ${project.name} is listed")
        projectLink(projectContainer(project.name), project.name).shouldBe(visible)
    }

    fun shouldListBuildForProject(project: Project, build: Build) {
        log.info("Checking if build ${build.name} is listed")
        projectContainer(project.name).find("[href*=buildConfiguration][title=${build.name}]").shouldBe(visible)
    }
}