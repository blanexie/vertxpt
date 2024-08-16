package com.github.blanexie.vxpt.crawler.model;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Date;

/**
 * @author xiezc
 * @date 2024/8/16 9:29
 */
@TargetUrl("https://juejin.cn/post/*")
public class Text implements AfterExtractor {

    @ExtractByUrl
    private String url;
    @ExtractBy(value = "//*[@id=\"juejin\"]/div[1]/main/div/div[1]/article/p/text()", notNull = true)
    private String title;
    @ExtractBy(value = "//*[@id=\"juejin\"]/div[1]/main/div/div[1]/article/div[3]/div[1]/div[1]/a/span[1]/text()", notNull = true)
    private String author;

    @ExtractBy(value = "//*[@id=\"article-root\"]/div/html()", notNull = true)
    private String content;
    @ExtractBy(value = "//*[@id=\"juejin\"]/div[1]/main/div/div[1]/article/div[3]/div[1]/div[2]/time/text()")
    private String time;
    private Date fetchTime;


    @Override
    public void afterProcess(Page page) {
        this.fetchTime = new Date();
    }

    /****************************************************
     * 下面是idea自动生成的get和set方法， 属性有修改，请重新生成
     * 不要把业务方法写在下面
     **************************************************/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Date fetchTime) {
        this.fetchTime = fetchTime;
    }

}

