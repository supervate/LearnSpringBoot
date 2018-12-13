package com.learn.SchedualTaskManagement.Dao;

import com.learn.SchedualTaskManagement.pojo.JobInfo;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JobRepository extends CrudRepository<JobInfo,String> {
}
