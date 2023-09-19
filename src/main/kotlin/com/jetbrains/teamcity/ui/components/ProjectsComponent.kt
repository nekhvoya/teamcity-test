package com.jetbrains.teamcity.ui.components

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.jetbrains.teamcity.data.Build
import com.jetbrains.teamcity.data.Project
import org.openqa.selenium.By.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

class ProjectsComponent: BaseComponent(className("projectHierarchy"), "Projects Form") {
    private val projectRow = { projectName: String ->
        `$`(xpath("//tr[contains(@class, 'project')][.//a[contains(text(), '$projectName')]]"))
    }
    private val projectArrow = { projectRow: SelenideElement ->
        projectRow.find(className("handle")) }

    private val buildLink = { buildName: String -> `$`(xpath("//tr[contains(@class, 'build_type')]//a[contains(., '$buildName')]")) }

    companion object {
        val log: Logger = getLogger(ProjectsComponent::class.java.simpleName)
    }

    fun expandProject(project: Project) {
        log.info("Expanding project $project on $componentName")
        if (isProjectExpanded(project)) {
            log.warn("Project ${project.name} is already expanded on $componentName")
            return
        }
        projectArrow(projectRow(project.name)).click()
    }

    fun isProjectExpanded(project: Project): Boolean {
        log.info("Checking if project $project is expanded on $componentName")
        return projectArrow(projectRow(project.name)).getAttribute("class")?.toBoolean() ?: false
    }

    fun clickBuildLink(build: Build) {
        log.info("Clicking link build ${build.name} on $componentName")
        buildLink(build.name).click()
    }
}