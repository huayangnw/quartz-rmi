package com.github.zhengzhiren.rmi;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhengzhiren on 2017/5/2.
 */
public class QuartzServer {

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(QuartzServer.class);

        try {
            SchedulerFactory sf = new StdSchedulerFactory("rmi_server.properties");

            Scheduler sched = sf.getScheduler();

            log.info("------- Initialization Complete -----------");

            log.info("------- (Not Scheduling any Jobs - relying on a remote client to schedule jobs --");

            log.info("------- Starting Scheduler ----------------");

            // start the schedule
            sched.start();

            log.info("------- Started Scheduler -----------------");

            Thread.sleep(6000L * 1000L);

            // shut down the scheduler
            log.info("------- Shutting Down ---------------------");
            sched.shutdown(true);
            log.info("------- Shutdown Complete -----------------");

            SchedulerMetaData metaData = sched.getMetaData();
            log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
