package com.github.blanexie.vxpt.ioc;

/**
 * 2022/12/9 2:58 下午
 * 容器加载初始化完成后执行的逻辑, 根据权重顺序执行
 *
 * @author xiezhicheng
 */
public interface AppLineRunner {

    default int order() {
        return 1;
    }

    void process();

}
