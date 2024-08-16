package com.github.blanexie.vxpt.bbs

import com.github.blanexie.vxpt.bbs.user.dao.UserMapper
import com.github.blanexie.vxpt.bbs.user.meta.entity.UserDO
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert
import java.util.function.Consumer
import javax.annotation.Resource

@SpringBootTest
class MybatisPlusTest(@Resource val userMapper: UserMapper) {

    @Test
    fun test(){
        val userList = userMapper.selectList(null)
        Assert.isTrue(1 == userList.size, "")
        userList.forEach(Consumer { x: UserDO? -> println(x) })
    }
}