package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.AdminSidebarItem.Companion.AGENT_REQUIREMENTS
import com.jetbrains.teamcity.AgentRequirement.Companion.TEAMCITY_AGENT_NAME
import com.jetbrains.teamcity.AgentStatus.Companion.AUTHORIZED
import com.jetbrains.teamcity.AgentStatus.Companion.CONNECTED
import com.jetbrains.teamcity.BuildType.Companion.SIMPLE_RUNNER
import com.jetbrains.teamcity.StepProperty.Companion.SCRIPT_CONTENT
import com.jetbrains.teamcity.StepProperty.Companion.STEP_MODE
import com.jetbrains.teamcity.StepProperty.Companion.USE_CUSTOM_SCRIPT
import com.jetbrains.teamcity.api.AgentsApi
import com.jetbrains.teamcity.api.BuildApi
import com.jetbrains.teamcity.api.ProjectsApi
import com.jetbrains.teamcity.data.*
import com.jetbrains.teamcity.utils.DockerManager
import com.jetbrains.teamcity.utils.randomString
import org.testng.annotations.*
import java.net.InetAddress.getLocalHost
import java.time.Duration.ofSeconds


class ConfigureBuildAgentTest: BaseTest() {
    private val dockerManger: DockerManager = DockerManager()
    private lateinit var agentId: String
    private lateinit var agentName: String

    private lateinit var createdProject: Project
    private lateinit var createdBuild: Build

    @BeforeTest
    fun setUpDockerContainer() {
        agentName = randomString()
        agentId = dockerManger.createAgentContainer("http://${getLocalHost().hostAddress}:8111", agentName)
    }

    @BeforeMethod
    fun createBuild() {
        createdProject = Project(randomString(), randomString())
        ProjectsApi(currentUser).createProject(createdProject)

        createdBuild = Build(
            randomString(),
            randomString(),
            createdProject,
            Steps(listOf(
                Step(randomString(), SIMPLE_RUNNER,
                Properties(listOf(
                    Property(SCRIPT_CONTENT, "pwd && echo test"),
                    Property(STEP_MODE, "default"),
                    Property(USE_CUSTOM_SCRIPT, "true"),
                ))
                )
            ))
        )
        BuildApi(currentUser).createBuild(createdBuild)
    }

    @UserAccount("ADMIN")
    @Test
    fun configureBuildAgentInDocker() {
        // Configure agent
        projectsPage.open()
        projectsPage.shouldBeOpened()
        projectsPage.header.clickAgents()
        agentsOverviewPage.shouldBeOpened()
        agentsOverviewPage.shouldDisplayUnauthorizedAgents()
        agentsOverviewPage.expandUnauthorizedAgents()
        agentsOverviewPage.shouldListAgent(agentName)
        agentsOverviewPage.clickAgent(agentName)
        agentDetailsPage.shouldBeOpened()
        agentDetailsPage.shouldDisplayConnectionStatus(CONNECTED)
        agentDetailsPage.authorizeAgent()
        agentDetailsPage.agentActionPopUp.submitAction()
        agentDetailsPage.shouldDisplayAuthorizationStatus(AUTHORIZED)

        // Configure agent requirements
        agentDetailsPage.header.clickAdministration()
        administrationPage.projectsForm.shouldBeVisible()
        administrationPage.projectsForm.expandProject(createdProject)
        administrationPage.projectsForm.clickBuildLink(createdBuild)
        editBuildPage.shouldBeOpened()
        editBuildPage.sidebar.selectMenuItem(AGENT_REQUIREMENTS)
        editRequirementsPage.shouldBeOpened()
        editRequirementsPage.clickAddNewRequirement()
        editRequirementsPage.editRequirementDialog.shouldBeVisible()
        val requirement = Requirement(TEAMCITY_AGENT_NAME, "equals", agentName)
        editRequirementsPage.editRequirementDialog.createRequirement(requirement)
        editRequirementsPage.shouldDisplayRequirementsUpdatedMessage()

        // Run build on configured agent
        agentDetailsPage.header.clickProjects()
        projectsPage.shouldBeOpened()

        projectsPage.expandProject(createdProject)
        projectsPage.shouldListBuildForProject(createdProject, createdBuild)
        projectsPage.runBuild(createdBuild)
        projectsPage.shouldListSuccessfulBuild(createdBuild, ofSeconds(20))
        projectsPage.shouldListAgentForBuild(createdBuild, agentName)
    }

    @AfterMethod(alwaysRun = true)
    fun cleanUpProject() {
        if (this::createdProject.isInitialized) {
            ProjectsApi(currentUser).deleteProject(createdProject.id)
        }
    }

    @AfterTest(alwaysRun = true)
    fun removeAgent() {
        if (this::agentId.isInitialized) {
            dockerManger.discardAgentContainer(agentId)
        }
        if (this::agentName.isInitialized) {
           AgentsApi(currentUser).enableAgent(agentName, false)
           AgentsApi(currentUser).deleteAgent(agentName)
        }
    }
}