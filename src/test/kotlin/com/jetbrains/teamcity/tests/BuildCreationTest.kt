package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.api.ProjectsApi
import com.jetbrains.teamcity.config.UserCredentials.ADMIN
import com.jetbrains.teamcity.data.Build
import com.jetbrains.teamcity.data.Project
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class BuildCreationTest: BaseTest() {
    private lateinit var createdProject: Project

    @BeforeMethod
    fun createNewProject() {
        createdProject = Project(randomAlphabetic(10), randomAlphabetic(10))
        ProjectsApi(currentUser).createProject(createdProject)
    }

    @User(ADMIN)
    @Test
    fun createBuildManually() {
        projectsPage.open()
        projectsPage.shouldBeOpened()
        projectsPage.shouldListProject(createdProject)
        projectsPage.addBuildConfiguration(createdProject)
        createBuildPage.shouldBeOpened()
        val createdBuild: Build = createBuildPage.createRandomBuild()
        editVcsRootPage.shouldBeOpened()
        editVcsRootPage.shouldHaveBuildConfigurationCreatedMessage()
        editVcsSettingsPage.header.clickProjects()
        projectsPage.shouldBeOpened()
        projectsPage.refresh()
        projectsPage.expandProject(createdProject)
        projectsPage.shouldListBuildForProject(createdProject, createdBuild)
    }

    @AfterMethod(alwaysRun = true)
    fun cleanUpProject() {
        if (this::createdProject.isInitialized) {
            ProjectsApi(currentUser).deleteProject(createdProject.id)
        }
    }
}