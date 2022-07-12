package com.github.blanexie.vxpt;

import com.github.blanexie.vxpt.auth.domain.factory.PathFactory;
import com.github.blanexie.vxpt.auth.domain.service.PathService;
import com.github.blanexie.vxpt.auth.domain.service.RoleService;
import com.github.blanexie.vxpt.auth.domain.factory.RoleFactory;
import com.github.blanexie.vxpt.auth.support.PathFactoryImpl;
import com.github.blanexie.vxpt.user.domain.factory.AccountEntityFactory;
import com.github.blanexie.vxpt.user.domain.factory.InvitationEntityFactory;
import com.github.blanexie.vxpt.user.domain.factory.UserEntityFactory;
import com.github.blanexie.vxpt.user.domain.service.AccountService;
import com.github.blanexie.vxpt.user.domain.service.InvitationService;
import com.github.blanexie.vxpt.user.domain.service.UserService;
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

    @Bean
    public PathService pathService(PathFactory pathFactory) {
        return new PathService(pathFactory);
    }

    @Bean
    public UserService userService(UserEntityFactory userEntityFactory, AccountEntityFactory accountEntityFactory,
                                   InvitationEntityFactory invitationEntityFactory) {
        return new UserService(userEntityFactory, accountEntityFactory, invitationEntityFactory);
    }

    @Bean
    public InvitationService invitationService(AccountEntityFactory accountEntityFactory, InvitationEntityFactory invitationEntityFactory) {
        return new InvitationService(invitationEntityFactory, accountEntityFactory);
    }

    @Bean
    public AccountService accountService(AccountEntityFactory accountEntityFactory) {
        return new AccountService(accountEntityFactory);
    }
}
