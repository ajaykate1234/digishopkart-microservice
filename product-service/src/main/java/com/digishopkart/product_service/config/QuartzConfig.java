package com.digishopkart.product_service.config;

import com.digishopkart.product_service.util.ProductExpiryJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(ProductExpiryJob.class)
                .withIdentity("productExpiryJob")
                .storeDurably()
                .build();
    }


// This Bean Trigger at every day at midnight
    @Bean
    public Trigger trigger(){
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("productExpiryTrigger")
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0,0))
                .build();   // Runs every day at midnight
    }


    // Use that bean to test for every 5 seconds
/*    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("productExpiryTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5) // Runs every 5 seconds
                        .repeatForever())
                .build();
   }*/
}
