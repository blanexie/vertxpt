package com.github.blanexie.vxpt;

import com.github.blanexie.vxpt.auth.domain.RoleService;
import com.github.blanexie.vxpt.auth.domain.factory.RoleFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
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
