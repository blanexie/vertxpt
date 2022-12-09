package com.github.blanexie.vxpt.tracker.repository

import cn.hutool.core.io.resource.ClassPathResource
import cn.hutool.core.io.resource.ResourceUtil
import cn.hutool.core.lang.Singleton
import cn.hutool.core.util.ClassUtil
import com.alibaba.druid.pool.DruidDataSource
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.TransactionFactory
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import java.nio.charset.Charset
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



fun buildDataSource(): DataSource {
    return Singleton.get("dataSource") {
        val druidDataSource = DruidDataSource()

        druidDataSource.init()
        druidDataSource
    }
}


