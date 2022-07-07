package com.github.blanexie.vxpt.common.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * @author ：xiezc
 * @date ：2022/7/7 4:41 PM
 */
public class AuthConfigurationSelector {


    @Bean
    @ConditionalOnBean(org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping.class)
    public ServletWebRequestMapping servletWebRequestMapping() {
        return new ServletWebRequestMapping();
    }

    @Bean
    @ConditionalOnBean(org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping.class)
    public WebFluxRequestMapping webFluxRequestMapping() {
        return new WebFluxRequestMapping();
    }

}
