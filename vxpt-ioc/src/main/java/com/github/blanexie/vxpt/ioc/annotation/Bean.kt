package com.github.blanexie.vxpt.ioc.annotation


/**
 * 注释的方法必须是无参的方法,
 * 通过次注释放入容器中对象,其内部不能在使用这个注解, (否则会无限嵌套)
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class Bean