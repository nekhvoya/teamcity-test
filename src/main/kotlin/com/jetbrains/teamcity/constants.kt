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

class VcsProperty {
    companion object {
        const val AUTH = "authMethod"
        const val BRANCH = "branch"
        const val URL ="url"
    }
}

class Cookie {
    companion object {
        const val SESSION = "TCSESSIONID"
        const val REMEMBER_ME = "RememberMe"
    }
}

class Runner {
    companion object {
        const val POWER_SHELL = "PowerShell"
        const val MAVEN = "Maven"
    }
}

class VcsType {
    companion object {
        const val GIT = "Git"
    }
}

class BuildOverviewTab {
    companion object {
        const val BUILD_LOG = "Build Log"
    }
}

class BuildResult {
    companion object {
        const val SUCCESS = "Success"
    }
}

class AdminSidebarItem {
    companion object {
        const val BUILD_STEPS = "editBuildRunners"
        const val USERS = "users"
        const val AGENT_REQUIREMENTS = "editRequirement"
    }
}

class BuildSidebarItem {
    companion object {
        const val TRIGGERS = "editTriggers"
        const val VCS = "editBuildTypeVcsRoots"
    }
}

class PopUpMenuItem {
    companion object {
        const val LOG_OUT = "Logout"
    }
}

class AgentStatus {
    companion object {
        const val CONNECTED = "Connected"
        const val AUTHORIZED = "Authorized"
    }
}

class AgentRequirement {
    companion object {
        const val TEAMCITY_AGENT_NAME = "teamcity.agent.name"
    }
}

class Trigger {
    companion object {
        const val VCS_TRIGGER = "VCS Trigger"
    }
}

