package com.github.blanexie.vxpt.crawler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiezc
 * @date 2024/8/16 10:29
 */
@MapperScan("com.github.blanexie.vxpt.crawler.*.dao")
@SpringBootApplication
public class CrawlerApplication {


    public static void main(String[] args) {
        try {
            SpringApplication.run(CrawlerApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
