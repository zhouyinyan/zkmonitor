package com.zbj.monitor.appconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by zhouyinyan on 17/5/3.
 */
@Configuration
@EnableScheduling
public class TaskSchedulerConfig {

    @Autowired
    ApplicationContext context;

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("scheduler thread pool ");
        taskScheduler.initialize();

        return taskScheduler;
    }


    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(100000);
        taskExecutor.setThreadNamePrefix("event listener thread pool ");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster applicationEventMulticaster(){
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster(context);
        multicaster.setTaskExecutor(taskExecutor());
        return multicaster;
    }

}
