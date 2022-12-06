package com.github.blanexie.vxpt.tracker.common

@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class Mapping(val uri: String)
