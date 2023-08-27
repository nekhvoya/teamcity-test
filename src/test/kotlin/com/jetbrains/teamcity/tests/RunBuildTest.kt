package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.api.BuildApi
import com.jetbrains.teamcity.api.ProjectsApi
import com.jetbrains.teamcity.BuildType.Companion.SIMPLE_RUNNER
import com.jetbrains.teamcity.StepProperty.Companion.SCRIPT_CONTENT
import com.jetbrains.teamcity.StepProperty.Companion.STEP_MODE
import com.jetbrains.teamcity.StepProperty.Companion.USE_CUSTOM_SCRIPT
import com.jetbrains.teamcity.data.*
import com.jetbrains.teamcity.utils.randomString
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.time.Duration.ofSeconds

class RunBuildTest: BaseTest() {
    private lateinit var createdProject: Project
    private lateinit var createdBuild: Build

    @BeforeMethod
    fun createNewProject() {
        createdProject = Project(randomString(), randomString())
        ProjectsApi(currentUser).createProject(createdProject)

        createdBuild = Build(
            randomString(),
            randomString(),
            createdProject,
            Steps(listOf(Step(randomString(), SIMPLE_RUNNER,
                Properties(listOf(
                    Property(SCRIPT_CONTENT, "echo ${randomString()}"),
                    Property(STEP_MODE, "default"),
                    Property(USE_CUSTOM_SCRIPT, "true"),
                )))))
        )
        BuildApi(currentUser).createBuild(createdBuild)
    }

    @UserAccount("ADMIN")
    @Test
    fun runBuild() {
        projectsPage.open()
        projectsPage.shouldBeOpened()
        projectsPage.expandProject(createdProject)
        projectsPage.shouldListBuildForProject(createdProject, createdBuild)
        projectsPage.runBuild(createdBuild)
        projectsPage.shouldListSuccessfulBuild(createdBuild, ofSeconds(10))
    }

    @AfterMethod(alwaysRun = true)
    fun cleanUpProject() {
        if (this::createdProject.isInitialized) {
            ProjectsApi(currentUser).deleteProject(createdProject.id)
        }
    }
}