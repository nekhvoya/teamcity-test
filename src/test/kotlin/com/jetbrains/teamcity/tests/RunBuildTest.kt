package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.api.BuildApi
import com.jetbrains.teamcity.api.ProjectsApi
import com.jetbrains.teamcity.config.UserCredentials.ADMIN
import com.jetbrains.teamcity.data.*
import com.jetbrains.teamcity.data.BuildType.SIMPLE_RUNNER
import com.jetbrains.teamcity.data.StepProperty.*
import com.jetbrains.teamcity.utils.randomName
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.time.Duration.ofSeconds

class RunBuildTest: BaseTest() {
    private lateinit var createdProject: Project
    private lateinit var createdBuild: Build

    @BeforeMethod
    fun createNewProject() {
        createdProject = Project(randomAlphabetic(10), randomAlphabetic(10))
        ProjectsApi(currentUser).createProject(createdProject)

        createdBuild = Build(
            randomName(),
            randomName(),
            createdProject,
            Steps(listOf(Step(randomAlphabetic(10), SIMPLE_RUNNER.propertyName,
                Properties(listOf(
                    Property(SCRIPT_CONTENT.propertyName, "echo test"),
                    Property(STEP_MODE.propertyName, "default"),
                    Property(USE_CUSTOM_SCRIPT.propertyName, "true"),
                )))))
        )
        BuildApi(currentUser).createBuild(createdBuild)
    }

    @User(ADMIN)
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