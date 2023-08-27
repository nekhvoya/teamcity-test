package com.jetbrains.teamcity.api

import com.jetbrains.teamcity.config.EnvConfig
import com.jetbrains.teamcity.data.Project
import com.jetbrains.teamcity.data.User
import io.restassured.builder.ResponseSpecBuilder
import org.apache.http.HttpStatus.SC_NO_CONTENT
import org.apache.http.HttpStatus.SC_OK
import org.hamcrest.Matchers.equalTo


class ProjectsApi(user: User): BaseApi(EnvConfig.PROJECTS_ENDPOINT, user) {

    fun createProject(project: Project) {
        requestSpecification
            .body(project)
            .post().then().spec(
                ResponseSpecBuilder()
                    .expectStatusCode(equalTo(SC_OK))
                    .build()
            )
    }

    fun deleteProject(projectId: String) {
        requestSpecification
            .delete("id:$projectId")
            .then().spec(
                ResponseSpecBuilder()
                    .expectStatusCode(equalTo(SC_NO_CONTENT))
                    .build()
            )
    }
}