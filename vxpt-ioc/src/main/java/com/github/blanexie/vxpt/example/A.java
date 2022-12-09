package com.github.blanexie.vxpt.example;

import com.github.blanexie.vxpt.ioc.annotation.Bean;
import com.github.blanexie.vxpt.ioc.annotation.Component;

/**
 * 2022/12/9 3:14 下午
 *
 * @author xiezhicheng
 */
@Component
public class A {

    public void print() {
        System.out.println("this is A");
    }

    @Bean
    public B build() {
        return new B(12);
    }

}
