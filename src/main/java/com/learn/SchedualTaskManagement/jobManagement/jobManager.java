package com.learn.SchedualTaskManagement.jobManagement;

import com.learn.SchedualTaskManagement.Job.BaseJob;
import com.learn.SchedualTaskManagement.pojo.JobInfo;
import com.learn.SchedualTaskManagement.config.JobConstant;
import com.learn.pojo.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class jobManager {

    private Scheduler scheduler;

    public jobManager(){
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            log.error("调度器创建失败！",e );
        }
    }

    public ResponseMessage addJob(JobInfo jobInfo) {
        if (jobInfo == null){
            return ResponseMessage.GetErrorMessage(String.format("JobInfo不能为Null!"));
        }
        if (!JobConstant.JOB_STATUS_RUN.equals(jobInfo.getStatus())){
            return ResponseMessage.GetErrorMessage(String.format("该工作状态为停用！请先开启该工作！JobId为:%s",jobInfo.getJobId()));
        }
        try {
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobInfo.getJobId()).withSchedule(CronScheduleBuilder.cronSchedule(jobInfo.getCronExpress())).build();
            JobDataMap params = new JobDataMap();
            params.put("jobInfo", jobInfo);
            JobDetail jobDetail;
            if (JobConstant.JOBTYPE_SPECIAl.equals(jobInfo.getJobType())) {
                Class JobClass = Class.forName(jobInfo.getJobClass());
                jobDetail = JobBuilder.newJob(JobClass).withIdentity(jobInfo.getJobId()).setJobData(params).build();
            } else {
                jobDetail = JobBuilder.newJob(BaseJob.class).withIdentity(jobInfo.getJobId()).setJobData(params).build();
            }
            scheduler.scheduleJob(jobDetail, trigger);
            return ResponseMessage.GetSuccessMessage(jobInfo);
        } catch (SchedulerException e) {
            log.error("Scheduler调度器出现异常！", e);
            return ResponseMessage.GetErrorMessage(String.format("Scheduler调度器出现异常！Cause : %s", e.getMessage()));
        } catch (ClassNotFoundException e) {
            log.error("添加自定义工作类失败,Class不存在!ClassName为:[%s]",e,jobInfo.getJobClass());
            return ResponseMessage.GetErrorMessage(String.format("添加自定义工作类失败,Class不存在!ClassName为:[%s]", e.getMessage(),jobInfo.getJobClass()));
        }
    }

    public ResponseMessage stopAllJob() {
        try {
            scheduler.pauseAll();
            return ResponseMessage.GetSuccessMessage("暂停内存中所有工作成功！");
        } catch (SchedulerException e) {
            log.error("暂停所有工作失败！",e );
            return ResponseMessage.GetErrorMessage(String.format("暂停内存中所有工作失败！Cause:%s", e.getMessage()));
        }
    }

    public ResponseMessage removeAllJob(){
        try {
            scheduler.clear();
            return ResponseMessage.GetSuccessMessage("移除内存中的所有工作成功！");
        } catch (SchedulerException e) {
            log.error("清空调度器任务列表失败！",e );
            return ResponseMessage.GetErrorMessage(String.format("清空调度器任务列表失败！Cause:%s",e.getMessage() ));
        }
    }

    public ResponseMessage activitiJob(JobInfo jobInfo) {
        if (jobInfo == null){
            return ResponseMessage.GetErrorMessage(String.format("JobInfo不能为Null!"));
        }
        try {
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
            if (scheduler.getJobDetail(new JobKey(jobInfo.getJobId())) == null){
                    return addJob(jobInfo);
            }
            scheduler.resumeJob(new JobKey(jobInfo.getJobId()));
            return ResponseMessage.GetSuccessMessage(jobInfo);
        } catch (SchedulerException e) {
            log.error(String.format("激活工作失败！JobId: %s", jobInfo.getJobId()),e );
            return ResponseMessage.GetErrorMessage(String.format("激活工作失败！JobId: %s Cause : %s",jobInfo.getJobId(),e.getMessage()));
        }
    }

    public ResponseMessage stopJob(String JobId) {
        try {
            if (scheduler.isShutdown()) {
                return ResponseMessage.GetSuccessMessage(JobId);
            }
            else if (!scheduler.isStarted()){
                return ResponseMessage.GetSuccessMessage(JobId);
            }
            else {
                scheduler.pauseJob(new JobKey(JobId));
                return ResponseMessage.GetSuccessMessage(JobId);
            }
        } catch (SchedulerException e) {
            log.error(String.format("工作关闭失败！JobId:", JobId),e );
            return ResponseMessage.GetErrorMessage(String.format("工作关闭失败！JobId:%s Cause: %s", JobId,e.getMessage()));
        }
    }

    public ResponseMessage removeJob(String jobId){
        try {
            scheduler.deleteJob(new JobKey(jobId));
            return ResponseMessage.GetSuccessMessage(jobId);
        } catch (SchedulerException e) {
            log.error(String.format("从调度器中移除工作失败！JobId:",jobId ),e);
            return ResponseMessage.GetErrorMessage(String.format("从调度器中移除工作失败！JobId:%s Cause:%s",jobId,e.getMessage() ));
        }
    }

    public boolean isExists(String jobId){
        try {
            boolean isExist = scheduler.checkExists(new JobKey(jobId));
            return isExist;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResponseMessage getAllExistsJob(){
        try {
            Set<JobKey> jobkeyList = scheduler.getJobKeys(GroupMatcher.anyGroup());
            List<String> jobIds = new ArrayList<>();
            for (JobKey jobKey : jobkeyList) {
                jobIds.add(jobKey.getName());
            }
            return ResponseMessage.GetSuccessMessage(jobIds);
        } catch (SchedulerException e) {
            return ResponseMessage.GetErrorMessage(String.format("获取当前内存中所有工作失败！Cause:%s", e.getMessage()));
        }
    }

    public void init(List<JobInfo> jobInfoList){
        if (!jobInfoList.isEmpty()){
            for (JobInfo jobInfo : jobInfoList) {
                addJob(jobInfo);
            }
        }
        try {
            scheduler.start();
        } catch (SchedulerException e) {
//            e.printStackTrace();
            log.error("调度器启动异常！",e );
        }
    }

    public static void main(String[] args) throws InterruptedException {
        jobManager jobManager = new jobManager();
        List<JobInfo> jobInfos = new ArrayList<>();
        JobInfo jobInfo = new JobInfo();
        jobInfo.setJobId("test1");
        jobInfo.setCronExpress("*/1 * * * * ?");
        jobInfos.add(jobInfo);
        jobManager.init(jobInfos);
        jobManager.stopJob(jobInfo.getJobId());
        Thread.sleep(5000);
        jobManager.activitiJob(jobInfo);
//        jobManager.pauseJob(jobInfo.getJobId());
        jobManager.removeJob(jobInfo.getJobId());
        Thread.sleep(5000);
        System.out.println(jobManager.activitiJob(jobInfo));
    }
}
