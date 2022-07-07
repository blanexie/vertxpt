package com.github.blanexie.vxpt.common;

/**
 * @author ：xiezc
 * @date ：2022/7/7 4:40 PM
 */
public @interface Auth {

    /**
     * 权限code
     *
     * @return
     */
    String value();

    /**
     * 描述信息
     *
     * @return
     */
    String description();

}
