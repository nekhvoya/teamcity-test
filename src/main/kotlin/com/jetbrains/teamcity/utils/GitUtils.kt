package com.jetbrains.teamcity.utils

import org.apache.commons.io.FileUtils.forceDelete
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.createTempDirectory

class GitRepo {
    val tempRepo: Path
    val git: Git
    val url: String
    val branch: String

    init {
        tempRepo = createTempDirectory("")
        git = Git.init().setDirectory(tempRepo.toFile()).call()
        url = (git.repository as FileRepository).directory.absolutePath
        branch = (git.repository as FileRepository).branch
    }

    fun commitFile(fileName: String, script: String): Path {
        val path: Path = Paths.get(tempRepo.toFile().absolutePath, fileName)
        Files.write(path, script.toByteArray(StandardCharsets.UTF_8))
        git.add().addFilepattern(".").call()
        git.commit().setMessage(randomString()).call()
        return path
    }

    fun tearDown() {
        git.close()
        forceDelete(tempRepo.toFile())
    }
}