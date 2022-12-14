package com.github.blanexie.vxpt.iocweb.annotation


@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class Mapping(val path: String, val permissionCode: Array<String> = [])
