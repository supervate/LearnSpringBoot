package com.learn.SchedualTaskManagement.Job;

import com.learn.SchedualTaskManagement.pojo.JobInfo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BaseJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        jobExecutionContext.getJobDetail();
        JobInfo jobInfo = (JobInfo) jobExecutionContext.getJobDetail().getJobDataMap().get("jobInfo");
        System.out.println(String.format("%s 正在运行哦！", jobInfo.getJobName()));
    }
}
