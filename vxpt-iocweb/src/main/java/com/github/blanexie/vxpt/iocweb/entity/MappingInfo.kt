package com.github.blanexie.vxpt.iocweb.entity

import com.github.blanexie.vxpt.iocweb.annotation.Mapping
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

class MappingInfo(
    val mapping: Mapping,
    val method: KFunction<*>,
    val clazz: KClass<*>
)

