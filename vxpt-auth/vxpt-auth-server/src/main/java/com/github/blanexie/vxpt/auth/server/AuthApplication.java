package com.github.blanexie.vxpt.auth.server;


import com.github.blanexie.vxpt.auth.server.domain.RoleService;
import com.github.blanexie.vxpt.auth.server.domain.factory.RoleFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class AuthApplication {


    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }

    /**
     * 领域内的bean， 可以隔离，不会把Spring的注解带入领域内
     *
     * @param roleFactory
     * @return
     */
    @Bean
    public RoleService roleService(RoleFactory roleFactory) {
        return new RoleService(roleFactory);
    }

}
