package com.github.blanexie.vxpt.ioc.process

interface AppCompleteExecute {

    fun order(): Int {
        return 100
    }

    fun execute()

}