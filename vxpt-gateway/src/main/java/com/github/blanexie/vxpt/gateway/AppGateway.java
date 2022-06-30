package com.github.blanexie.vxpt.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AppGateway {

    public static void main(String[] args) {
        SpringApplication.run(AppGateway.class, args);
    }
}
