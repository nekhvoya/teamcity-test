package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.config.UserCredentials
import com.jetbrains.teamcity.data.Project
import io.restassured.builder.ResponseSpecBuilder
import org.hamcrest.Matchers.equalTo


class ProjectsApi(user: UserCredentials): BaseApi(EnvConfig.projectsEndpoint, user) {

    fun createProject(project: Project) {
        requestSpecification
            .body(project)
            .post()
    }

    fun deleteProject(projectId: String) {
        requestSpecification
            .delete("id:$projectId")
            .then().spec(
                ResponseSpecBuilder()
                    .expectStatusCode(equalTo(204))
                    .build()
            )
    }
}