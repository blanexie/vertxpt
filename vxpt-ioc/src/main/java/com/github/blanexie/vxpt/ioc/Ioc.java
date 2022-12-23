package com.github.blanexie.vxpt.ioc;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.blanexie.vxpt.ioc.annotation.Bean;

import com.github.blanexie.vxpt.ioc.annotation.Component;
import com.github.blanexie.vxpt.ioc.annotation.Inject;
import com.github.blanexie.vxpt.ioc.process.AppCompleteExecute;
import com.github.blanexie.vxpt.ioc.process.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;


public class Ioc {

    private static Logger log = LoggerFactory.getLogger(Ioc.class);

    public static final String BEAN_SCAN_SPI_PACKAGE = "com.github.blanexie.vxpt.ioc.spi";

    public static void load(Class<?> clazz) throws Exception {
        log.info("IOC load start.");
        //加载配置文件
        loadProperties();
        //扫描bean类
        scanCurrentPackage(clazz);
        //spi机制, 可以自动装配
        scanSpiPackage();
        //钩子, 允许自定义加入容器
        scanBeanMethod();
        //依赖注入对象
        inject();
        //钩子, 在依赖注入完成后进行的一些操作
        doAppLineRunner();
        log.info("IOC load end.");
    }

    private static void doAppLineRunner() {
        Set<Class<?>> existClass = Singleton.getExistClass();
        existClass.stream().filter(it -> AppCompleteExecute.class.isAssignableFrom(it))
                .map(it -> (AppCompleteExecute) Singleton.get(it))
                .sorted(Comparator.comparing(AppCompleteExecute::order))
                .forEach(it -> {
                    log.info("IOC  appLineRunner start,  class:{} ", it.getClass());
                    it.execute();
                    log.info("IOC  appLineRunner end,  class:{} ", it.getClass());
                });
    }

    private static void inject() throws IllegalAccessException {
        Set<Class<?>> existClass = Singleton.getExistClass();
        for (Class<?> aClass : existClass) {
            injectField(aClass);
        }
        log.info("IOC inject obj end, Initialization is complete , load all class size:{} ", existClass.size());
    }

    private static void scanBeanMethod() throws IllegalAccessException, InvocationTargetException {
        Set<Class<?>> existClass = Singleton.getExistClass();
        for (Class<?> aClass : existClass) {
            List<Method> publicMethods = ClassUtil.getPublicMethods(aClass, m -> m.getAnnotation(Bean.class) != null);
            Object obj = Singleton.get(aClass);
            for (Method publicMethod : publicMethods) {
                if (publicMethod.getParameterCount() > 0) {
                    throw new RuntimeException(StrUtil.format("Bean 不能注释在有参的构造方法上面, class:{} method:{}", aClass.getName(), publicMethod.getName()));
                }
                Singleton.put(publicMethod.invoke(obj));
            }
        }
        log.info("IOC scan @Bean method end");
    }

    private static void scanSpiPackage() {
        Set<Class<?>> spiClasses = ClassUtil.scanPackageByAnnotation(BEAN_SCAN_SPI_PACKAGE, Component.class);
        Set<String> scanPackages = new HashSet<>();
        for (Class<?> aClass : spiClasses) {
            Object instance = ReflectUtil.newInstance(aClass);
            Singleton.put(instance);
            if (ComponentScan.class.isAssignableFrom(aClass)) {
                ComponentScan componentScan = (ComponentScan) instance;
                String[] packages = componentScan.scanPackages();
                scanPackages.addAll(ListUtil.of(packages));
            }
        }

        for (String scanPackage : scanPackages) {
            Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(scanPackage, Component.class);
            //遍历 初始化对象
            for (Class<?> aClass : classes) {
                Singleton.put(ReflectUtil.newInstance(aClass));
            }
        }

        log.info("IOC scan SPI Package:{} end, load Class size:{}", BEAN_SCAN_SPI_PACKAGE, spiClasses.size());
    }

    private static void scanCurrentPackage(Class<?> clazz) {
        String aPackage = ClassUtil.getPackage(clazz);
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(aPackage, Component.class);
        //遍历 初始化对象
        for (Class<?> aClass : classes) {
            Singleton.put(ReflectUtil.newInstance(aClass));
        }
        log.info("IOC scanPackage:{} end, load Class size:{}", aPackage, classes.size());
    }


    private static void injectField(Class<?> aClass) throws IllegalAccessException {
        for (Field field : ReflectUtil.getFields(aClass)) {
            Inject annotation = field.getAnnotation(Inject.class);
            if (annotation != null) {
                Object obj = getFromSingleton(aClass);
                Class<?> type = field.getType();
                Object arg = getFromSingleton(type);
                field.setAccessible(true);
                field.set(obj, arg);
            }
        }
    }

    private static Object getFromSingleton(Class<?> clazz) {
        if (Singleton.exists(clazz)) {
            return Singleton.get(clazz);
        }
        Set<Class<?>> existClass = Singleton.getExistClass();
        List<Class<?>> collect = existClass.stream().filter(it -> clazz.isAssignableFrom(it))
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
        Properties properties = new Properties();
        try (BufferedReader reader = ResourceUtil.getReader("app.properties", Charset.forName("utf8"))) {
            properties.load(reader);
            Singleton.put(properties);
        } catch (IOException e) {
            log.error("未找到配置文件  app.properties");
            // throw new RemoteException("加载配置文件 app.properties失败 ", e);
        }
        log.info("IOC loadProperties end.");
        return properties;
    }
}
