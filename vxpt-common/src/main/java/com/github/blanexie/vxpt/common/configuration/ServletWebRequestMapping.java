package com.github.blanexie.vxpt.common.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ：xiezc
 * @date ：2022/7/7 7:40 PM
 */
public class ServletWebRequestMapping implements CommandLineRunner {

    @Resource
    RequestMappingHandlerMapping handlerMapping;

    @Override
    public void run(String... args) throws Exception {
        //启动后扫描容器中的所有接口
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        handlerMethods.forEach((mapping,method)->{
            Set<String> patterns = mapping.getPatternsCondition().getPatterns();



        });



        for (Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
            RequestMappingInfo mapping = item.getKey();
            final HandlerMethod method = item.getValue();
            for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
                //请求的方式,  post 还是get
                final Set<RequestMethod> methods = mapping.getMethodsCondition().getMethods();
                for (RequestMethod requestMethod : methods) {

                }
            }
        }

    }

}
