package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.BuildOverviewTab.Companion.BUILD_LOG
import com.jetbrains.teamcity.BuildResult.Companion.SUCCESS
import com.jetbrains.teamcity.Runner.Companion.MAVEN
import com.jetbrains.teamcity.api.ProjectsApi
import com.jetbrains.teamcity.config.GitRepositories
import com.jetbrains.teamcity.data.Project
import com.jetbrains.teamcity.data.VcsRoot
import com.jetbrains.teamcity.utils.randomString
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test
import java.time.Duration

class ConfigureBuildFromVcsTest: BaseTest() {
    private val createdProject: Project = Project(randomString())
    private val buildName: String = "Test Java Build 01"
    private val stepName: String = "Run Main"
    private val output: String = "Hello world!"

    @UserAccount("ADMIN")
    @Test
    fun configureBuildFromVcs() {
        // Configure project from VCS
        projectsPage.open()
        projectsPage.shouldBeOpened()
        projectsPage.createNewProject()
        createProjectPage.shouldBeOpened()
        createProjectPage.clickCreateFromUrlButton()
        createProjectPage.createProjectFromUrlForm.shouldBeVisible()
        val vscConfig: VcsRoot = GitRepositories.repositories.getValue("test-java-project")
        createProjectPage.createProjectFromUrlForm.createProject(vscConfig)
        projectSetupPage.shouldBeOpened()
        projectSetupPage.shouldHaveSelectedImportSettingsRadioButton()
        projectSetupPage.changeProjectName(createdProject)
        projectSetupPage.clickProceed()
        projectSetupPage.waitSaving()
        editProjectPage.shouldBeOpened()
        editProjectPage.shouldHaveProjectCreatedFromVcsMessage()
        editProjectPage.shouldListBuildConfiguration(buildName)
        editProjectPage.shouldListBuildStep(stepName)

        // Run project from VCS
        editProjectPage.openBuildConfiguration(buildName)
        editBuildPage.shouldBeOpened()
        editBuildPage.clickRun()
        buildConfigurationPage.shouldBeOpened()
        buildConfigurationPage.buildOverview.shouldBeVisible()
        buildConfigurationPage.shouldDisplayResult(SUCCESS, Duration.ofSeconds(15))
        buildConfigurationPage.openOverviewTab(BUILD_LOG)
        buildConfigurationPage.buildLog.openStepLog(MAVEN)
        buildConfigurationPage.buildLog.shouldDisplayOutPutLog(output)
    }

    @AfterMethod(alwaysRun = true)
    fun cleanUpProject() {
        ProjectsApi(currentUser).deleteProject(createdProject.id)
    }
}