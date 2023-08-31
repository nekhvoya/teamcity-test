package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.AdminSidebarItem.Companion.BUILD_STEPS
import com.jetbrains.teamcity.BuildOverviewTab.Companion.BUILD_LOG
import com.jetbrains.teamcity.BuildResult.Companion.SUCCESS
import com.jetbrains.teamcity.Runner.Companion.POWER_SHELL
import com.jetbrains.teamcity.VcsType
import com.jetbrains.teamcity.api.ProjectsApi
import com.jetbrains.teamcity.data.Project
import com.jetbrains.teamcity.data.VcsRoot
import com.jetbrains.teamcity.utils.GitRepo
import com.jetbrains.teamcity.utils.randomString
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.nio.file.Path
import java.time.Duration.ofSeconds

    class ConfigureBuildTest: BaseTest() {
    private lateinit var createdProject: Project
    private lateinit var createdVcsRoot: VcsRoot
    private lateinit var git: GitRepo

    @BeforeMethod
    fun setUpGitRepo() {
        git = GitRepo()
        createdVcsRoot = VcsRoot(randomString(), git.url, git.branch)
    }

    @UserAccount("ADMIN")
    @Test
    fun createFirstBuild() {
        // Create new project
        projectsPage.open()
        projectsPage.shouldBeOpened()
        projectsPage.createNewProject()
        createProjectPage.shouldBeOpened()
        createdProject = createProjectPage.createRandomProject()
        editProjectPage.shouldBeOpened()
        editProjectPage.shouldHaveProjectCreatedMessage()

        // Create build configuration
        editProjectPage.clickCreateBuild()
        createBuildPage.shouldBeOpened()
        createBuildPage.createRandomBuild()
        editVcsRootPage.shouldBeOpened()

        // Configure VCS
        val output: String = randomString()
        val scriptFile: Path = git.commitFile("test.ps1", "echo ${output}")

        editVcsRootPage.selectVcsType(VcsType.GIT)
        editVcsRootPage.setVcsName(createdVcsRoot.name)
        editVcsRootPage.setUrl(createdVcsRoot.url)
        editVcsRootPage.setBranch(createdVcsRoot.branch)
        editVcsRootPage.save()
        editVcsSettingsPage.shouldBeOpened()
        editVcsSettingsPage.shouldHaveVcsRootUpdatedMessage()

        // Configure build step
        editVcsSettingsPage.sideBar.selectMenuItem(BUILD_STEPS)
        editBuildRunnersPage.shouldBeOpened()
        editBuildRunnersPage.clickAddBuildStep()
        newBuildStepPage.shouldBeOpened()
        newBuildStepPage.selectRunner(POWER_SHELL)
        newBuildStepPage.powerShellForm.shouldBeVisible()
        newBuildStepPage.powerShellForm.setScriptFile(scriptFile.toFile().absolutePath)
        newBuildStepPage.powerShellForm.save()
        editBuildRunnersPage.shouldBeOpened()
        editBuildRunnersPage.shouldHaveSettingsUpdatedMessage()


        // Run build
        editBuildRunnersPage.breadcrumbs.runFirstBuild()

        // View results
        buildConfigurationPage.shouldBeOpened()
        buildConfigurationPage.buildOverview.shouldBeVisible()
        buildConfigurationPage.shouldDisplayResult(SUCCESS, ofSeconds(10))
        buildConfigurationPage.openOverviewTab(BUILD_LOG)
        buildConfigurationPage.buildLog.openStepLog(POWER_SHELL)
        buildConfigurationPage.buildLog.shouldDisplayOutPutLog(output)
    }

    @AfterTest(alwaysRun = true)
    fun cleanUpProject() {
        if (this::createdProject.isInitialized) {
            ProjectsApi(currentUser).deleteProject(createdProject.id)
        }
        if (this::git.isInitialized) {
           git.tearDown()
        }
    }
}
