package com.jetbrains.teamcity.tests

import com.jetbrains.teamcity.config.UserCredentials

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class User(val user: UserCredentials)
