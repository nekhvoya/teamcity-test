package com.jetbrains.teamcity.utils

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.command.CreateContainerResponse
import com.github.dockerjava.api.command.PullImageResultCallback
import com.github.dockerjava.api.model.Bind
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.Volume
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientBuilder
import com.jetbrains.teamcity.config.TestAgentConfig
import org.apache.commons.io.FileUtils.forceDelete
import java.net.InetAddress.getLocalHost
import java.nio.file.Path
import kotlin.io.path.createTempDirectory

class DockerManager {
    val config: DefaultDockerClientConfig
    val dockerClient: DockerClient
    val tempConfig: Path

    init {
        tempConfig = createTempDirectory("")
        config = DefaultDockerClientConfig
            .createDefaultConfigBuilder()
            .withDockerHost(TestAgentConfig.DOCKER_HOST)
            .build()
        dockerClient = DockerClientBuilder.getInstance(config).build()
    }

    fun createAgentContainer(agentName: String): String {
        dockerClient.pullImageCmd(TestAgentConfig.AGENT_IMAGE).exec(PullImageResultCallback()).awaitCompletion()
        val container: CreateContainerResponse = dockerClient
            .createContainerCmd(TestAgentConfig.AGENT_IMAGE)
            .withEnv("SERVER_URL=http://${getLocalHost().hostAddress}:8111", "AGENT_NAME=$agentName")
            .withHostConfig(
                HostConfig.newHostConfig().withBinds(Bind(tempConfig.toFile().absolutePath, Volume(TestAgentConfig.AGENT_CONFIG_PATH))))
            .exec()
        dockerClient
            .startContainerCmd(container.id)
            .exec()
        return container.id
    }

    fun discardAgentContainer(id: String) {
        dockerClient.stopContainerCmd(id).exec()
        dockerClient.removeContainerCmd(id).exec()
        forceDelete(tempConfig.toFile())
    }
}