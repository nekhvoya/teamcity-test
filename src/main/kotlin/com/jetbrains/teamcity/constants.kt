package com.jetbrains.teamcity

class BuildType {
    companion object {
        const val SIMPLE_RUNNER = "simpleRunner"
    }
}

class StepProperty {
    companion object {
        const val SCRIPT_CONTENT = "script.content"
        const val STEP_MODE = "teamcity.step.mode"
        const val USE_CUSTOM_SCRIPT ="use.custom.script"
    }
}

class Cookie {
    companion object {
        const val SESSION = "TCSESSIONID"
    }
}

class Runner {
    companion object {
        const val CMD = "Command Line"
    }
}