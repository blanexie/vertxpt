group = "com.github.blanexie.vxpt.crawler"
version = "0.0.1"

plugins {
    //模块需要其他插件, 在这里写
}

dependencies {
    implementation("us.codecraft:webmagic-core")
    implementation("us.codecraft:webmagic-extension")
    implementation("us.codecraft:webmagic-saxon")

    implementation("cn.hutool:hutool-all")

    implementation("org.xerial:sqlite-jdbc")
    implementation("com.baomidou:mybatis-plus-boot-starter")
    implementation("org.mybatis:mybatis-typehandlers-jsr310")
    implementation("com.alibaba:fastjson")
}