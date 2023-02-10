group = "com.github.blanexie.vxpt.bbs"
version = "0.0.1"

plugins {

}

noArg {
    "com.baomidou.mybatisplus.annotation.TableName"
}

dependencies {
    //模块需要其他第三方库, 在这里写
    implementation("org.xerial:sqlite-jdbc:3.21.0.1")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.0")
    implementation("org.mybatis:mybatis-typehandlers-jsr310:1.0.2")
    implementation("com.alibaba:fastjson:2.0.23")

}