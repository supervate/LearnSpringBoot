package com.learn.makeTestData.dao;

import com.learn.makeTestData.pojo.UserInfoDO;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfoDO,Integer> {
}
