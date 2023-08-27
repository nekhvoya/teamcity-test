package com.jetbrains.teamcity.config

import com.jetbrains.teamcity.data.User

class UserAccounts {
    companion object {
        val users = HashMap<String, User>()

        init {
            users["ADMIN"] = User("admin", "admin")
        }
    }
}