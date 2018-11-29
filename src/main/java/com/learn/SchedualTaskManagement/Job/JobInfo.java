package com.learn.SchedualTaskManagement.Job;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class JobInfo {
    @Id
    private String jobId;
    @Column
    private String jobName;
    @Column
    private String cronExpress;

}
