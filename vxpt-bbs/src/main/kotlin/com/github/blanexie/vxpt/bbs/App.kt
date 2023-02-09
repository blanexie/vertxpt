package com.github.blanexie.vxpt.bbs


import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VxptBbsApplication : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {

    }
}

fun main(args: Array<String>) {
    runApplication<VxptBbsApplication>(*args)
}