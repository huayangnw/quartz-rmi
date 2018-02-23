package com.github.zhengzhiren.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MyService {

    private static Logger logger = LoggerFactory.getLogger(MyService.class);

    public void print(String jobKey, String message) {
        logger.info("RemoteJob: " + jobKey + " executing at " + new Date());
        logger.info("RemoteJob: msg: " + message);
    }
}
