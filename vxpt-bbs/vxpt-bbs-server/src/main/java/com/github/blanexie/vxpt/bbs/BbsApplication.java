package com.github.blanexie.vxpt.bbs;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.web.servlet.ControllerEndpointHandlerMapping;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.github.blanexie.vxpt.api.*.feign")
public class BbsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BbsApplication.class);
    }

    @Resource
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * 扫描项目获取所有的api
     *
     * @return
     */
    public void scanSpringRequestApi() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
            RequestMappingInfo mapping = item.getKey();
            final HandlerMethod method = item.getValue();
            //请求的方式,  post 还是get
            final Set<RequestMethod> methods = mapping.getMethodsCondition().getMethods();
            for (RequestMethod requestMethod : methods) {
                final Class<?> beanType = method.getBeanType();
                String name = beanType.getSimpleName() + "#" + method.getMethod().getName();
                String s = beanType.getName() + "#" + method.getMethod().getName();
                log.info("name：{}  setMethod:{}  setDescription:{} ", name, requestMethod.name(), s);
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        this.scanSpringRequestApi();
    }

}
