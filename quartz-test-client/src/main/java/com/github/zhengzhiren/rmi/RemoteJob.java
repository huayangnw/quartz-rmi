package com.github.zhengzhiren.rmi;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteJob implements Job {

    public static final String MESSAGE = "msg";

    private static Logger logger = LoggerFactory.getLogger(RemoteJob.class);

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        JobKey jobKey = context.getJobDetail().getKey();

        String message = (String) context.getJobDetail().getJobDataMap().get(MESSAGE);

        MyService myService = new MyService();
        myService.print(jobKey.toString(), message);
    }


}
