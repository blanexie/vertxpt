package com.github.blanexie.vxpt.ioc.spi

import com.github.blanexie.vxpt.ioc.process.ComponentScan
import com.github.blanexie.vxpt.ioc.annotation.Component

@Component
class HttpComponentScan : ComponentScan {

    override fun scanPackages(): Array<String> {
        return arrayOf("com.github.blanexie.vxpt.iocweb")
    }
}