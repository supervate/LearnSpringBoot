package com.learn.SchedualTaskManagement.pojo;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@ToString
@Entity
public class JobInfo {
    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid",strategy = "uuid")
    private String jobId;
    @Column
    private String status;
    @Column
    private String jobName;
    @Column
    private String cronExpress;
    @Column
    private String jobType;
    @Column
    private String jobClass;

    public JobInfo() {}

    public JobInfo(String jobName, String cronExpress, String jobType, String jobClass) {
        this.jobName = jobName;
        this.cronExpress = cronExpress;
        this.jobType = jobType;
        this.jobClass = jobClass;
    }
}
