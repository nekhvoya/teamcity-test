package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.api.ProjectsApi
import com.jetbrains.teamcity.data.Project
import org.testng.annotations.AfterTest
import org.testng.annotations.Test

class ProjectCreationTest: BaseTest() {
    private lateinit var createdProject: Project

    @UserAccount("ADMIN")
    @Test
    fun createNewProjectManually() {
        projectsPage.open()
        projectsPage.shouldBeOpened()
        projectsPage.createNewProject()
        createProjectPage.shouldBeOpened()
        createdProject = createProjectPage.createRandomProject()
        editProjectPage.shouldBeOpened()
        editProjectPage.shouldHaveProjectCreatedMessage()
        editProjectPage.save()
        editProjectPage.shouldHaveProjectUpdatedMessage()
        editProjectPage.header.clickProjects()
        projectsPage.shouldBeOpened()
        projectsPage.refresh()
        projectsPage.shouldListProject(createdProject)
    }

    @AfterTest(alwaysRun = true)
    fun cleanUpProject() {
        if (this::createdProject.isInitialized) {
            ProjectsApi(currentUser).deleteProject(createdProject.id)
        }
    }
}
