package com.example.learningdemo.QuartzDemo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.Cookie;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
public class QuartzConfig {
    public static void main(String[] args) {
        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // start
            scheduler.start();

            // define the job and tie it to our SimpleJob class
            JobDetail job = JobBuilder.newJob(QuartzJobTest.class).withIdentity("job1",
                    "group1").build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(
                            simpleSchedule().withIntervalInHours(1)
                                    .repeatForever()).build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            // shutdown if needed
            // scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        Cookie token = new Cookie("Token", "123");
//        token.set
    }

    @Bean(name = "FiveSecondJob")
    public JobDetail FiveSecondJob() {
        return JobBuilder.newJob(QuartzJobTest.class).withIdentity("FiveSecondJob").build();

    }

    @Bean(name = "FiveSecondTrigger")
    public Trigger FiveSecondTrigger(@Qualifier("FiveSecondJob") JobDetail jobDetail) {
        return newTrigger().startNow()
                .forJob(jobDetail).withIdentity("FiveSecondTrigger")
                .withSchedule(simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();
    }
}

