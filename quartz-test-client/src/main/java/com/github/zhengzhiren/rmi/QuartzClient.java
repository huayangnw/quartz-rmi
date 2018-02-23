package com.github.zhengzhiren.rmi;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by zhengzhiren on 2017/5/2.
 */
public class QuartzClient {
    private static final Logger log = LoggerFactory.getLogger(QuartzClient.class);

    public static void main(String[] args) {
        try {
            SchedulerFactory sf = new StdSchedulerFactory("rmi_client.properties");
            Scheduler sched = sf.getScheduler();

            JobDetail job = newJob(RemoteJob.class)
                    .withIdentity("remotelyAddedJob", "default")
                    .build();

            JobDataMap map = job.getJobDataMap();
            map.put(RemoteJob.MESSAGE, "Your remotely added job has executed!");

            Trigger trigger = newTrigger()
                    .withIdentity("remotelyAddedTrigger", "default")
                    .forJob(job.getKey())
                    .withSchedule(cronSchedule("0/5 * * ? * *"))
                    .build();

            sched.scheduleJob(job, trigger);

//            Thread.sleep(120 * 1000);
//            sched.shutdown();

            log.info("Remote job scheduled.");
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
