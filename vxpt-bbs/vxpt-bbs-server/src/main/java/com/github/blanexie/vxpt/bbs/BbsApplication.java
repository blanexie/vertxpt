package com.github.blanexie.vxpt.bbs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Slf4j
@EnableJpaAuditing
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackageClasses = BbsApplication.class)
public class BbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbsApplication.class);
    }

}
