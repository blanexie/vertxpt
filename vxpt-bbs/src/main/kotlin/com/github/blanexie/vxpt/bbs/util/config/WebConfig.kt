package com.github.blanexie.vxpt.bbs.util.config

import com.alibaba.fastjson.serializer.SerializeConfig
import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.serializer.ToStringSerializer
import com.alibaba.fastjson.support.config.FastJsonConfig
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.math.BigInteger
import java.nio.charset.Charset

@Configuration
@ConditionalOnClass(WebMvcConfigurer::class)
@Order(Ordered.HIGHEST_PRECEDENCE)
open class WebConfig : WebMvcConfigurer {

    constructor() : super()

    @Bean
    open fun customConverters(): HttpMessageConverters {
        //创建fastJson消息转换器

        var fastJsonConverter = FastJsonHttpMessageConverter()
        //创建配置类
        var fastJsonConfig = FastJsonConfig()
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
            // 格式化
            SerializerFeature.PrettyFormat,
            // 可解决long精度丢失 但会有带来相应的中文问题
            //SerializerFeature.BrowserCompatible,
            // 消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
            SerializerFeature.DisableCircularReferenceDetect,
            // 是否输出值为null的字段,默认为false
            SerializerFeature.WriteMapNullValue,
            // 字符类型字段如果为null,输出为"",而非null
            SerializerFeature.WriteNullStringAsEmpty,
            // List字段如果为null,输出为[],而非null
            SerializerFeature.WriteNullListAsEmpty
        )
        // 日期格式
        fastJsonConfig.dateFormat = "yyyy-MM-dd HH:mm:ss"

        // long精度问题
        var serializeConfig = SerializeConfig.globalInstance
        serializeConfig.put(Integer::class.java, ToStringSerializer.instance)
        serializeConfig.put(BigInteger::class.java, ToStringSerializer.instance)
        serializeConfig.put(Long::class.java, ToStringSerializer.instance)
        serializeConfig.put(Long::class.javaObjectType, ToStringSerializer.instance)
        fastJsonConfig.setSerializeConfig(serializeConfig)

        //处理中文乱码问题
        var fastMediaTypes = ArrayList<MediaType>()
        fastMediaTypes.add(MediaType.APPLICATION_JSON)
        fastMediaTypes.add(MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8")))
        fastMediaTypes.add(MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8")))
        fastMediaTypes.add(MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8")))
        fastMediaTypes.add(MediaType.MULTIPART_FORM_DATA)

        fastJsonConverter.setSupportedMediaTypes(fastMediaTypes)
        fastJsonConverter.setFastJsonConfig(fastJsonConfig)
        //将fastjson添加到视图消息转换器列表内
        return HttpMessageConverters(fastJsonConverter)
    }


}