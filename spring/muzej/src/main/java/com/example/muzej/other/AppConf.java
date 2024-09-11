package com.example.muzej.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class AppConf {

    @Autowired
    EmailThread emailThread;

    @Bean(name = "jedan")
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor(); 
    }

    @Bean
    public CommandLineRunner schedulingRunner(@Qualifier("jedan") TaskExecutor executor) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                executor.execute(emailThread);
            }
        };
    }
}
