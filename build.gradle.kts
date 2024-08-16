plugins {
    id("org.springframework.boot") version "2.5.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.spring") version "1.5.10"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.4.20"
}

repositories {
    mavenLocal()
    maven {
        setUrl("https://maven.aliyun.com/nexus/content/groups/public/")
    }
    mavenCentral()
}

//注意以下内容
subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "idea")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.noarg")

    repositories {
        mavenLocal()
        maven {
            setUrl("https://maven.aliyun.com/nexus/content/groups/public/")
        }
        mavenCentral()
    }

    java.sourceCompatibility = JavaVersion.VERSION_11

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib")

        implementation("org.springframework.boot:spring-boot-starter-web")

        developmentOnly("org.springframework.boot:spring-boot-devtools")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    dependencyManagement {
        dependencies {
            dependency("us.codecraft:webmagic-core:1.0.0")
            dependency("us.codecraft:webmagic-extension:1.0.0")
            dependency("us.codecraft:webmagic-saxon:1.0.0")
            dependency("cn.hutool:hutool-all:5.8.31")

            //模块需要其他第三方库, 在这里写
            dependency("org.xerial:sqlite-jdbc:3.21.0.1")
            dependency("com.baomidou:mybatis-plus-boot-starter:3.5.0")
            dependency("org.mybatis:mybatis-typehandlers-jsr310:1.0.2")
            dependency("com.alibaba:fastjson:2.0.23")

        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}