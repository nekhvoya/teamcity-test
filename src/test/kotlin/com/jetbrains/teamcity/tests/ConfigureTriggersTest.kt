package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.BuildSidebarItem.Companion.TRIGGERS
import com.jetbrains.teamcity.BuildSidebarItem.Companion.VCS
import com.jetbrains.teamcity.BuildType
import com.jetbrains.teamcity.StepProperty
import com.jetbrains.teamcity.Trigger.Companion.VCS_TRIGGER
import com.jetbrains.teamcity.VcsProperty
import com.jetbrains.teamcity.api.BuildApi
import com.jetbrains.teamcity.api.ProjectsApi
import com.jetbrains.teamcity.api.VcsApi
import com.jetbrains.teamcity.api.VcsInstancesApi
import com.jetbrains.teamcity.data.*
import com.jetbrains.teamcity.utils.GitRepo
import com.jetbrains.teamcity.utils.createFile
import com.jetbrains.teamcity.utils.randomString
import org.testng.annotations.*
import java.nio.file.Path
import java.nio.file.Paths
import java.time.Duration.ofSeconds

class ConfigureTriggersTest: BaseTest() {
    private lateinit var git: GitRepo

    private lateinit var createdProject: Project
    private lateinit var createdBuild: Build
    private lateinit var createdVcs: VcsRoot

    @BeforeTest
    fun setUpGitRepo() {
        git = GitRepo()
        val initCommit: Path = createFile(Paths.get(git.tempRepo.toFile().absolutePath, "init.txt"), randomString())
        git.commitFile(initCommit)
    }

    @BeforeMethod
    fun createBuild() {
        createdProject = Project(randomString(), randomString())
        ProjectsApi(currentUser).createProject(createdProject)

        createdVcs = VcsRoot(
            id = randomString(),
            name = randomString(),
            vcsName = "jetbrains.git",
            project = createdProject,
            properties = Properties(listOf(
                Property(VcsProperty.AUTH, "ANONYMOUS"),
                Property(VcsProperty.BRANCH, git.branch),
                Property(VcsProperty.URL, git.url),
            ))
        )
        VcsApi(currentUser).createVcsRoot(createdVcs)

        createdBuild = Build(
            randomString(),
            randomString(),
            createdProject,
            Steps(listOf(
                Step(
                    randomString(), BuildType.SIMPLE_RUNNER,
                    Properties(listOf(
                        Property(StepProperty.SCRIPT_CONTENT, "echo ${randomString()}"),
                        Property(StepProperty.STEP_MODE, "default"),
                        Property(StepProperty.USE_CUSTOM_SCRIPT, "true"),
                    ))
                )
            )),
            VcsRootEntries(listOf(
                VcsRootEntry(
                    createdVcs.id!!,
                    createdVcs
                )
            ))
        )
        BuildApi(currentUser).createBuild(createdBuild)
    }

    @UserAccount("ADMIN")
    @Test
    fun configureVcsTrigger() {
        projectsPage.open()
        projectsPage.shouldBeOpened()

        // Wait for VCS changes to be registered
        projectsPage.header.clickAdministration()
        administrationPage.shouldBeOpened()
        administrationPage.projectsForm.expandProject(createdProject)
        administrationPage.projectsForm.clickBuildLink(createdBuild)
        editBuildPage.shouldBeOpened()
        editBuildPage.sidebar.selectMenuItem(VCS)
        editVcsSettingsPage.shouldBeOpened()
        editVcsSettingsPage.waitForCheckForChanges()

        // Configure VCS trigger
        editBuildPage.sidebar.selectMenuItem(TRIGGERS)
        editTriggersPage.shouldBeOpened()
        editTriggersPage.clickAddNewTrigger()
        editTriggersPage.editTriggerDialog.shouldBeVisible()
        editTriggersPage.editTriggerDialog.selectTrigger(VCS_TRIGGER)
        editTriggersPage.editTriggerDialog.clickSave()
        editTriggersPage.shouldDisplayTriggersUpdatedMessage()

        // Verify build is triggered on changes in VCS
        editTriggersPage.header.clickProjects()
        projectsPage.shouldBeOpened()
        projectsPage.expandProject(createdProject)
        val newFile: Path = createFile(Paths.get(git.tempRepo.toFile().absolutePath, "test.txt"), randomString())
        git.commitFile(newFile)
        VcsInstancesApi(currentUser).triggerCommitHook(createdVcs.id!!)
        projectsPage.shouldListSuccessfulBuild(createdBuild, ofSeconds(45))
    }

    @AfterMethod(alwaysRun = true)
    fun cleanUpProject() {
        if (this::createdProject.isInitialized) {
            ProjectsApi(currentUser).deleteProject(createdProject.id)
        }
    }

    @AfterTest(alwaysRun = true)
    fun tearDownGit() {
        if (this::git.isInitialized) {
            git.tearDown()
        }
    }
}