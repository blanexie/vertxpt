package com.github.blanexie.vxpt.bbs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;


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
