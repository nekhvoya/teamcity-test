package com.jetbrains.teamcity.config

import com.jetbrains.teamcity.data.VcsRoot

class GitRepositories {
    companion object {
        val repositories = HashMap<String, VcsRoot>()

        init {
            repositories.put("test-java-project",
                VcsRoot(name = "test-java-project",
                    url = "https://github.com/rgGXrzFksh/test-java-project.git",
                    defaultBranch = "master"))
        }

    }
}