package com.github.blanexie.vxpt.example;

import com.github.blanexie.vxpt.ioc.AppLineRunner;
import com.github.blanexie.vxpt.ioc.annotation.Component;
import com.github.blanexie.vxpt.ioc.annotation.Inject;

/**
 * 2022/12/9 3:16 下午
 *
 * @author xiezhicheng
 */
@Component
public class C implements AppLineRunner {

    @Inject
    public A a;

    @Inject
    public B b;

    @Override
    public int order() {
        return 0;
    }

    @Override
    public void process() {
        System.out.println("c AppLineRunner");
        a.print();
        b.print();
    }
}
