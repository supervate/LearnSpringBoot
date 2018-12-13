package com.learn.SchedualTaskManagement.Dao;

import com.learn.SchedualTaskManagement.pojo.JobInfo;

import java.util.Optional;

public class JobRepositoryImpl implements JobRepository {

    @Override
    public <S extends JobInfo> S save(S s) {
        return null;
    }

    @Override
    public <S extends JobInfo> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<JobInfo> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<JobInfo> findAll() {
        System.out.println("我是自定义方法");
        return null;
    }

    @Override
    public Iterable<JobInfo> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(JobInfo jobInfo) {

    }

    @Override
    public void deleteAll(Iterable<? extends JobInfo> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
