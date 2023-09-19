package com.jetbrains.teamcity.utils

import org.apache.commons.io.FileUtils.forceDelete
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import java.nio.file.Path
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

    fun commitFile(file: Path) {
        git.add().addFilepattern(file.fileName.toString()).call()
        git.commit().setMessage(randomString()).call()
    }

    fun tearDown() {
        git.close()
        forceDelete(tempRepo.toFile())
    }
}