package com.github.blanexie.vxpt.tracker.common;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

public class Ioc {

    public static void load(Class<?> clazz) throws IllegalAccessException {
        //扫描bean类
        String aPackage = ClassUtil.getPackage(clazz);
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(aPackage, Bean.class);
        //遍历 初始化对象
        for (Class<?> aClass : classes) {
            Singleton.put(aClass.getName(), ReflectUtil.newInstance(aClass));
        }
        //依赖注入对象
        for (Class<?> aClass : classes) {
            injectField(aClass);
            //找到Mapping注解映射
            getMappingMethod(aClass);
        }

    }

    private static void getMappingMethod(Class<?> aClass) {
        Method[] methods = ReflectUtil.getMethods(aClass, m -> m.getAnnotation(Mapping.class) != null);
        for (Method method : methods) {
            Mapping annotation = method.getAnnotation(Mapping.class);
            MappingMethodInfo mappingMethodInfo = new MappingMethodInfo();
            mappingMethodInfo.setMethod(method);
            mappingMethodInfo.setMapping(annotation);
            mappingMethodInfo.setClazz(aClass);
            mappingMethodInfo.setUrl(mappingMethodInfo.url);
            Singleton.put("_" + mappingMethodInfo.url, mappingMethodInfo);
        }
    }

    private static void injectField(Class<?> aClass) throws IllegalAccessException {
        for (Field field : ReflectUtil.getFields(aClass)) {
            Inject annotation = field.getAnnotation(Inject.class);
            if (annotation != null) {
                Object obj = Singleton.get(aClass.getName());
                Class<?> type = field.getType();
                Object obj2 = Singleton.get(type.getName());
                if (!field.canAccess(obj)) {
                    field.setAccessible(true);
                }
                field.set(obj, obj2);
            }
        }
    }

}
