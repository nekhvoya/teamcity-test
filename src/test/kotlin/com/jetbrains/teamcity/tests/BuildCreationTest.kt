package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.api.ProjectsApi
import com.jetbrains.teamcity.data.Build
import com.jetbrains.teamcity.data.Project
import com.jetbrains.teamcity.utils.randomString
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class BuildCreationTest: BaseTest() {
    private lateinit var createdProject: Project

    @BeforeMethod
    fun createNewProject() {
        createdProject = Project(randomString(), randomString())
        ProjectsApi(currentUser).createProject(createdProject)
    }

    @UserAccount("ADMIN")
    @Test
    fun createBuildManually() {
        projectsPage.open()
        projectsPage.shouldBeOpened()
        projectsPage.shouldListProject(createdProject)
        projectsPage.clickProject(createdProject)
        projectConfigurationPage.shouldBeOpened()
        projectConfigurationPage.clickEdit()
        editProjectPage.shouldBeOpened()
        editProjectPage.clickCreateBuild()
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