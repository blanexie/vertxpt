package com.github.blanexie.vxpt.ioc.web.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class Mapping(val uri: String)
