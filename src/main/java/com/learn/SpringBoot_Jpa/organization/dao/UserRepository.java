package com.learn.SpringBoot_Jpa.organization.dao;

import com.learn.SpringBoot_Jpa.organization.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
}
