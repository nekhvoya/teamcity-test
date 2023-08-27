package com.jetbrains.teamcity.constants


enum class BuildType(val propertyName: String) {
    SIMPLE_RUNNER("simpleRunner")
}

enum class StepProperty(val propertyName: String) {
    SCRIPT_CONTENT("script.content"),
    STEP_MODE("teamcity.step.mode"),
    USE_CUSTOM_SCRIPT("use.custom.script")
}