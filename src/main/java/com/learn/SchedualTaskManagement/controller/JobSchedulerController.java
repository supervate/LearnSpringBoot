package com.learn.SchedualTaskManagement.controller;

import com.learn.SchedualTaskManagement.Dao.JobRepository;
import com.learn.SchedualTaskManagement.Dao.JobRepositoryImpl;
import com.learn.SchedualTaskManagement.jobManagement.jobManager;
import com.learn.SchedualTaskManagement.pojo.JobInfo;
import com.learn.Utils.JpaUpdateUtil;
import com.learn.pojo.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.ObjIntConsumer;

@Slf4j
@RestController()
public class JobSchedulerController {
    @Autowired
    private jobManager jobManager;
    @Autowired
    private JobRepository jobRepository;

    @PutMapping("/jobs")
    @Transactional
    public ResponseMessage createJob(JobInfo jobInfo) throws NoSuchFieldException, IllegalAccessException {
        if (StringUtils.isNotBlank(jobInfo.getJobId())) {
            //如果有传入id则当作更新操作处理
            Optional<JobInfo> optionalJobInfo = jobRepository.findById(jobInfo.getJobId());
            if (optionalJobInfo.isPresent()) {
                //更新
                JobInfo DbJobInfo = optionalJobInfo.get();
                //将提交的对象中的非null得字段赋值给从数据库中查出来的对象的字段
                JpaUpdateUtil.copyNonNullProperties(jobInfo, DbJobInfo);
                //将被赋值过的DbJobInfo入库
                JobInfo updatedJobInfo = jobRepository.save(DbJobInfo);
                return ResponseMessage.GetSuccessMessage(updatedJobInfo);
            } else {
                //该id对应的对象不存在,禁止提交
                return ResponseMessage.GetErrorMessage(String.format("您所提交的JobId[%s]对应的对象不存在！请确认(如果是创建操作则请勿携带JobId参数)！", jobInfo.getJobId()));
            }
        }
        JobInfo createdJobInfo = jobRepository.save(jobInfo);
        return ResponseMessage.GetSuccessMessage(createdJobInfo);
    }

    @GetMapping("/jobs")
    public ResponseMessage getAllJob() {
        Iterator<JobInfo> iterator = jobRepository.findAll().iterator();
        List<JobInfo> jobInfos = new ArrayList<>();
        iterator.forEachRemaining(jobInfo -> {
            jobInfos.add(jobInfo);
        });
        return ResponseMessage.GetSuccessMessage(jobInfos);
    }

    @GetMapping("/jobs/{id}")
    public ResponseMessage getJob(@PathVariable(name = "id") String id) {
        Optional<JobInfo> optionalJobInfo = jobRepository.findById(id);
        if (optionalJobInfo.isPresent()) {
            return ResponseMessage.GetSuccessMessage(optionalJobInfo.get());
        } else {
            return ResponseMessage.GetErrorMessage(String.format("您所查找的工作对象不存在！工作Id为:%s", id));
        }
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseMessage delteJob(@PathVariable(name = "id") String id) {
        if (jobManager.isExists(id)) {
            jobManager.removeJob(id);
        }
        jobRepository.deleteById(id);
        return ResponseMessage.GetSuccessMessage(id);
    }

    @DeleteMapping("/jobs/all")
    public ResponseMessage delteAllJob() {
        Iterator<JobInfo> iterator = jobRepository.findAll().iterator();
        iterator.forEachRemaining(jobInfo -> {
            if (jobManager.isExists(jobInfo.getJobId())) {
                jobManager.removeJob(jobInfo.getJobId());
            }
        });
        return ResponseMessage.GetSuccessMessage("删除成功！");
    }


    @GetMapping("/jobs/list/{ids}")
    public ResponseMessage getJobs(@PathVariable(name = "ids") String ids) {
        String[] allId = ids.split(",");
        List<String> idList = new ArrayList();
        for (int i = 0; i < allId.length; i++) {
            idList.add(allId[i]);
        }
        List<JobInfo> jobInfos = new ArrayList<>();
        Iterator<JobInfo> iterator = jobRepository.findAllById(idList).iterator();
        iterator.forEachRemaining(jobInfo -> {
            jobInfos.add(jobInfo);
        });
        return ResponseMessage.GetSuccessMessage(jobInfos);
    }

    @PostMapping("/jobs/start/{id}")
    public ResponseMessage startJob(@PathVariable(name = "id") String id) {
        Optional<JobInfo> optionalJobInfo = jobRepository.findById(id);
        if (optionalJobInfo.isPresent()) {
            return jobManager.activitiJob(optionalJobInfo.get());
        } else {
            return ResponseMessage.GetErrorMessage(String.format("您所指定的工作并不存在!请确认已经创建该工作！当前工作Id为:[%s]", id));
        }
    }

    @PostMapping("/jobs/startsome/{ids}")
    public ResponseMessage startJobs(@PathVariable(name = "ids") String ids) {
        String[] allId = ids.split(",");
        List<String> idList = new ArrayList();
        for (int i = 0; i < allId.length; i++) {
            idList.add(allId[i]);
        }
        int failCount = 0;
        List<String> failIds = new ArrayList<>();
        Iterator<JobInfo> iterator = jobRepository.findAllById(idList).iterator();
        iterator.forEachRemaining(jobInfo -> {
            ResponseMessage responseMessage = jobManager.activitiJob(jobInfo);
            if (ResponseMessage.RESPONSE_FAIL.equals(responseMessage.getStatus())) {
                log.error(String.format("工作启动失败！工作Id为: %s! 错误详情: %s", jobInfo.getJobId(), responseMessage.getErrorinfo()));
            }
        });
        Map failInfo = new HashMap();
        failInfo.put("failCount", failCount);
        failInfo.put("failIds", failIds);
        return ResponseMessage.GetSuccessMessage(failInfo);
    }

    @PostMapping("/jobs/stop/{id}")
    public ResponseMessage stopJob(@PathVariable(name = "id") String id) {
        Optional<JobInfo> optionalJobInfo = jobRepository.findById(id);
        if (optionalJobInfo.isPresent()) {
            return jobManager.removeJob(id);
        } else {
            return ResponseMessage.GetErrorMessage(String.format("您所指定的工作并不存在!请确认已经创建该工作！当前工作Id为:[%s]", id));
        }
    }

    @PostMapping("/jobs/stopall")
    public ResponseMessage stopJob() {
        return jobManager.removeAllJob();
    }

    @GetMapping("/jobs/checkrun/{id}")
    public Boolean CheckIsExistsInMemorry(@PathVariable(name = "id") String id){
        return jobManager.isExists(id);
    }

    @GetMapping("/jobs/runs/all")
    public ResponseMessage getAllRunningJobInfo(){
        ResponseMessage runningJobsMessage = jobManager.getAllExistsJob();
        if (ResponseMessage.RESPONSE_FAIL.equals(runningJobsMessage.getStatus())){
            return ResponseMessage.GetErrorMessage(String.format("数据获取出现异常！错误原因:", runningJobsMessage.getErrorinfo()));
        }
        else {
            List<String> jobIds = (List<String>) runningJobsMessage.getData();
            List<JobInfo> jobs = new ArrayList<>();
            Iterator<JobInfo> JobIterator = jobRepository.findAllById(jobIds).iterator();
            JobIterator.forEachRemaining(JobInfo ->{
                jobs.add(JobInfo);
            });
            return ResponseMessage.GetSuccessMessage(jobs);
        }
    }


}
