package com.github.blanexie.vxpt.iocorm

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.lang.Singleton
import com.github.blanexie.vxpt.ioc.annotation.Component
import com.github.blanexie.vxpt.ioc.annotation.Inject
import com.github.blanexie.vxpt.ioc.process.AppCompleteExecute
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistry
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import java.util.*

@Component
class HibernateService : AppCompleteExecute {


    override fun order(): Int {
        return 0
    }

    @Inject
    lateinit var properties: Properties


    override fun execute() {
        val registry: StandardServiceRegistry = StandardServiceRegistryBuilder().configure()
            .applySettings(properties)
            .build()
        try {
            var metadataSources = MetadataSources(registry)
            properties.getProperty("hibernate.annotation.package")?.let {
                metadataSources.addPackage(it)
            }

            BeanUtil.
            var buildMetadata = metadataSources.buildMetadata()
            val sessionFactory = buildMetadata.buildSessionFactory()
            Singleton.put(sessionFactory)
        } catch (e: Exception) {
            StandardServiceRegistryBuilder.destroy(registry)
        }
    }


    fun openSession(): Session {
        var sessionFactory = Singleton.get(SessionFactory::class.java)
        return sessionFactory.openSession()
    }


}
