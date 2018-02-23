package com.github.zhengzhiren.HelloQuartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by zhengzhiren on 2017/5/2.
 */
public class HelloJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(HelloJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        logger.info("HelloJob (group={}, name={}) executed. {}", jobKey.getGroup(), jobKey.getName(), new Date());
    }
}
