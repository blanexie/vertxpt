package com.github.blanexie.vxpt.ioc.web;

import com.github.blanexie.vxpt.ioc.web.annotation.Mapping;

import java.lang.reflect.Method;


public class MappingInfo {

    String url;

    Mapping mapping;

    Method method;

    Class<?> clazz;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Mapping getMapping() {
        return mapping;
    }

    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
