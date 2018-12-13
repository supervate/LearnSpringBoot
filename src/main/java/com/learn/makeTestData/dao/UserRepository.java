package com.learn.makeTestData.dao;

import com.learn.makeTestData.pojo.UserDO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDO,Integer> {
}
