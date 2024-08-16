group = "com.github.blanexie.vxpt.bbs"
version = "0.0.1"

plugins {

}

noArg {
    "com.baomidou.mybatisplus.annotation.TableName"
}

dependencies {
    //模块需要其他第三方库, 在这里写
    implementation("org.xerial:sqlite-jdbc")
    implementation("com.baomidou:mybatis-plus-boot-starter")
    implementation("org.mybatis:mybatis-typehandlers-jsr310")
    implementation("com.alibaba:fastjson")

}