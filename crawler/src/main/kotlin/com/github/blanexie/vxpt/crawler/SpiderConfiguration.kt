package com.github.blanexie.vxpt.crawler

import com.github.blanexie.vxpt.crawler.model.Text
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.model.ConsolePageModelPipeline
import us.codecraft.webmagic.model.OOSpider
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler


/**
 *
 * @author xiezc
 * @date 2024/8/16 10:43
 */
@Configuration
class SpiderConfiguration : CommandLineRunner {

    override fun run(vararg args: String?) {

        OOSpider.create(Site.me().setSleepTime(1000))
                .addPageModel(ConsolePageModelPipeline(), Text::class.java)
                .setScheduler(FileCacheQueueScheduler("data/url"))
                .addUrl("https://juejin.cn/post/7033041594911883271")
                .runAsync()
    }


}