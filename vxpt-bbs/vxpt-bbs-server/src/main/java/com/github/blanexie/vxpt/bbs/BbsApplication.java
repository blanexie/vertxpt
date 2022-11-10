package com.github.blanexie.vxpt.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.github.blanexie.vxpt.api.*.feign")
public class BbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbsApplication.class);
    }
}
