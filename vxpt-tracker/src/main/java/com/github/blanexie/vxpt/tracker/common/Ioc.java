package com.github.blanexie.vxpt.tracker.common;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class Ioc {

    public static void load(Class<?> clazz) throws Exception {
        //加载配置文件
        loadProperties();

        //扫描bean类
        String aPackage = ClassUtil.getPackage(clazz);
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(aPackage, Bean.class);
        //遍历 初始化对象
        for (Class<?> aClass : classes) {
            Singleton.put(ReflectUtil.newInstance(aClass));
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
                Object obj = getFromSingleton(aClass);
                Class<?> type = field.getType();
                Object obj2 = getFromSingleton(type);
                if (!field.canAccess(obj)) {
                    field.setAccessible(true);
                }
                field.set(obj, obj2);
            }
        }
    }

    private static Object getFromSingleton(Class<?> clazz) {
        if (Singleton.exists(clazz)) {
            return Singleton.get(clazz);
        }
        Set<Class<?>> existClass = Singleton.getExistClass();
        List<Class<?>> collect = existClass.stream()
                .filter(it -> clazz.isAssignableFrom(it))
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            throw new RuntimeException("容器中无匹配clazz：" + clazz.getName() + " 的Bean");
        }
        if (collect.size() > 1) {
            throw new RuntimeException("容器中有多个匹配clazz：" + clazz.getName() + " 的Bean");
        }
        return Singleton.get(collect.get(0));
    }

    private static Properties loadProperties() throws IOException {
        var properties = new Properties();
        var reader = ResourceUtil.getReader("app.properties", Charset.forName("utf8"));
        properties.load(reader);
        properties.forEach((k,v)->{
            System.setProperty(k.toString(),v.toString());
        });

        return properties;
    }
}
