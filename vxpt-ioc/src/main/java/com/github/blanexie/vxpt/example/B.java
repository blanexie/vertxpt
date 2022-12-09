package com.github.blanexie.vxpt.example;

import com.github.blanexie.vxpt.ioc.AppLineRunner;

/**
 * 2022/12/9 3:14 下午
 *
 * @author xiezhicheng
 */
public class B implements AppLineRunner {

    int x;

    public B(int a) {
        this.x = a;
    }

    public void print(){
        System.out.println("this is B");
    }

    @Override
    public void process() {
        System.out.println("B AppLineRunner");
    }
}
