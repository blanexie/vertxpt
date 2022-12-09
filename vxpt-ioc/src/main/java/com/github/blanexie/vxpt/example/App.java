package com.github.blanexie.vxpt.example;

import com.github.blanexie.vxpt.ioc.Ioc;

/**
 * 2022/12/9 3:18 下午
 *
 * @author xiezhicheng
 */
public class App {

    public static void main(String[] args) throws Exception {
        Ioc.load(App.class);
    }
}
