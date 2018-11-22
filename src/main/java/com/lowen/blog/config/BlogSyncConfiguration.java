package com.lowen.blog.config;

import com.lowen.blog.service.spider.CSDNSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BlogSyncConfiguration implements ApplicationRunner {

    @Autowired
    private CSDNSpider mCSDNSpider;

    @Scheduled(cron = "0 0 4 ? * *")
    public void schedule() {
        log.debug("Sync task started...");
        mCSDNSpider.startCSDNSync();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("Application run....");
        mCSDNSpider.startCSDNSync();
    }
}
