package com.jetbrains.teamcity.config

class TestAgentConfig {
    companion object {
        const val DOCKER_HOST = "tcp://localhost:2375"
        const val AGENT_IMAGE = "jetbrains/teamcity-agent:2023.05.3"
        const val AGENT_CONFIG_PATH = "/data/teamcity_agent/conf"
    }
}