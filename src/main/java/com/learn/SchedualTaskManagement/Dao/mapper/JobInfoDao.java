package com.learn.SchedualTaskManagement.Dao.mapper;

import com.learn.SchedualTaskManagement.pojo.JobInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class JobInfoDao extends SqlSessionDaoSupport {

    public JobInfoDao(@Autowired SqlSessionFactory sqlSessionFactory){
        setSqlSessionFactory(sqlSessionFactory);
    }
}
