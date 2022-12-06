package com.github.blanexie.vxpt.tracker.repository

import cn.hutool.core.lang.Singleton
import com.alibaba.druid.pool.DruidDataSource
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.TransactionFactory
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import java.util.*
import javax.sql.DataSource


fun buildSqlSessionFactory(): SqlSessionFactory {
    return Singleton.get("sqlSessionFactory") {
        val dataSource: DataSource = buildDataSource()
        val transactionFactory: TransactionFactory = JdbcTransactionFactory()
        val environment = Environment("development", transactionFactory, dataSource)
        val configuration = Configuration(environment)
        configuration.addMappers(System.getProperty("mybatis.mapper"))
        configuration.typeAliasRegistry.registerAliases(System.getProperty("mybatis.typeAlias"))
        val sqlSessionFactory: SqlSessionFactory = SqlSessionFactoryBuilder()
            .build(configuration)
        sqlSessionFactory
    }
}


fun loadProperties(): Properties {
    return Singleton.get("tracker.properties") {
        var properties = Properties()
        val stream = Thread.currentThread().contextClassLoader.getResourceAsStream("tracker.properties")
        properties.load(stream)
        System.setProperties(properties)
        properties
    }

}

fun buildDataSource(): DataSource {
    return Singleton.get("dataSource") {

        val druidDataSource = DruidDataSource()
        druidDataSource.init()
        druidDataSource
    }
}


