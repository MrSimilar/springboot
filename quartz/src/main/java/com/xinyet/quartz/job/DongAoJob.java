package com.xinyet.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * 定义Job
 * 1. extends QuartzJobBean
 * 2. implements Job
 */
@Slf4j
//@PersistJobDataAfterExecution//持久化任务
//@DisallowConcurrentExecution//保证任务执行完成再执行下一个
public class DongAoJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("幼年是盼盼，青年是晶晶，中年是冰墩墩，生活见好逐渐发福");
    }
}