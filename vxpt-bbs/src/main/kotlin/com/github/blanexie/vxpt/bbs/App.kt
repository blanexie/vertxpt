package com.github.blanexie.vxpt.bbs


import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.annotation.Resource

@MapperScan("com.github.blanexie.vxpt.bbs.*.dao")
@SpringBootApplication
class VxptBbsApplication : ApplicationRunner {


    override fun run(args: ApplicationArguments?) {

    }
}

fun main(args: Array<String>) {
    runApplication<VxptBbsApplication>(*args)
}