package com.learn.SchedualTaskManagement.jobManagement;

import com.learn.SchedualTaskManagement.Job.BaseJob;
import com.learn.SchedualTaskManagement.Job.JobInfo;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class jobManager {

    public static final List<JobInfo>  allJobInfo = new ArrayList<>();
    @Resource(name = "newQuartzScheduler")
    Scheduler scheduler;

    public int startAllJob(){
        try {
            for (int i = 0; i < allJobInfo.size(); i++) {
                JobInfo jobInfo = allJobInfo.get(i);
                CronTriggerImpl trigger = new CronTriggerImpl();
                trigger.setName(jobInfo.getJobName());
                trigger.setCronExpression(jobInfo.getCronExpress());
                JobDetailImpl jobDetail = new JobDetailImpl();
                JobKey jobKey = new JobKey(jobInfo.getJobId());
                jobDetail.setKey(jobKey);
                jobDetail.setName(jobInfo.getJobName());
                jobDetail.setJobClass(BaseJob.class);
                scheduler.addJob(jobDetail,false);
                trigger.setJobKey(jobKey);
                scheduler.scheduleJob(jobDetail,trigger);
            }
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public int stopAllJob(){

    }

    public boolean startJob(){

    }

    public boolean stopJob(){

    }

    @PostConstruct
    public void init(){
        //初始化allJobInfo;
    }
}
