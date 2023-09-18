package com.jetbrains.teamcity.utils

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path

fun createFile(path: Path, script: String): Path {
    return Files.write(path, script.toByteArray(StandardCharsets.UTF_8))
}