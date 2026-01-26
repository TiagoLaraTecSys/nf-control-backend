package com.rich.nf_control.infrastructure.scheduler.config;

import com.rich.nf_control.infrastructure.scheduler.jobs.VerificarNotasAVencerJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail registrarNotasAVencerJobDetail() {
        return JobBuilder.newJob(VerificarNotasAVencerJob.class)
                .withIdentity("registar-notas-a-vencer-job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger registrarNotasAVencerTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(registrarNotasAVencerJobDetail())
                .withIdentity("registar-notas-a-vencer-trigger")
                .withSchedule(
                        CronScheduleBuilder.dailyAtHourAndMinute(0, 6)
                )
                .build();
    }

}