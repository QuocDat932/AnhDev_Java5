package com.quocdat.java5.config;

import com.quocdat.java5.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final Service service;

    public ScheduledTasks(Service service) {
        this.service = service;
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void deleteDisabledRecords() {
        service.deleteAllDisableRecords();
        log.info("Deleted all disabled records");
    }

    //    @Scheduled(cron = "3,5,7,11,17,23,37,41,47 * * * * MON-FRI")
//    public void reportCurrentTimeCronVer() {
//        log.info("* cron said: now is {}", dateFormat.format(new Date()));
//    }
//    @Scheduled(fixedDelay = 1137)
//    public void reportCurrentTimeDelayMode() {
//        log.info("** delay said: now is {}", dateFormat.format(new Date()));
//    }
    @Scheduled(fixedRate = 1000)
    public void reportCurrentTimeRatedMode() {
        log.info("*** rate said: now is {}", dateFormat.format(new Date()));
    }
}
