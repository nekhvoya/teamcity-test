package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.api.BuildApi
import com.jetbrains.teamcity.api.ProjectsApi
import com.jetbrains.teamcity.config.UserCredentials.ADMIN
import com.jetbrains.teamcity.constants.Runner.CMD
import com.jetbrains.teamcity.data.Build
import com.jetbrains.teamcity.data.CommandLineStep
import com.jetbrains.teamcity.data.Project
import com.jetbrains.teamcity.utils.randomString
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class BuildStepCreationTest: BaseTest() {
    private lateinit var createdProject: Project
    private lateinit var createdBuild: Build

    @BeforeMethod
    fun createNewProject() {
        createdProject = Project(randomString(), randomString())
        ProjectsApi(currentUser).createProject(createdProject)

        createdBuild = Build(randomString(), randomString(), createdProject)
        BuildApi(currentUser).createBuild(createdBuild)
    }

    @User(ADMIN)
    @Test
    fun createBuildStep() {
        projectsPage.open()
        projectsPage.shouldBeOpened()
        projectsPage.shouldListProject(createdProject)
        projectsPage.expandProject(createdProject)
        projectsPage.shouldListBuildForProject(createdProject, createdBuild)
        projectsPage.clickBuildFromProject(createdProject, createdBuild)
        buildConfigurationPage.shouldBeOpened()
        buildConfigurationPage.clickEdit()
        editBuildPage.shouldBeOpened()
        editBuildPage.adminSidebar.selectBuildSteps()
        editBuildRunnersPage.shouldBeOpened()
        editBuildRunnersPage.clickAddBuildStep()
        newBuildStepPage.shouldBeOpened()
        newBuildStepPage.selectRunner(CMD)
        val createdBuildStep = CommandLineStep(randomString(), "echo ${randomString()}")
        newBuildStepPage.commandLineForm.typeStepName(createdBuildStep.stepName)
        newBuildStepPage.commandLineForm.typeScript(createdBuildStep.customScript)
        newBuildStepPage.commandLineForm.save()
        editBuildRunnersPage.shouldBeOpened()
        editBuildRunnersPage.shouldHaveSettingsUpdatedMessage()
        editBuildRunnersPage.shouldListBuildStep(createdBuildStep)
    }

    @AfterMethod(alwaysRun = true)
    fun cleanUpProject() {
        if (this::createdProject.isInitialized) {
            ProjectsApi(currentUser).deleteProject(createdProject.id)
        }
    }
}