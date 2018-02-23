package com.github.zhengzhiren.HelloQuartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by zhengzhiren on 2017/4/27.
 */
public class QuartzTest {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();

            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("job1")
                    .build();

            ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1")
                    .startNow()
                    .withSchedule(scheduleBuilder)
                    .build();

            scheduler.scheduleJob(job, trigger);


            Thread.sleep(60000);
            scheduler.shutdown();
        } catch (SchedulerException se) {
            se.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
