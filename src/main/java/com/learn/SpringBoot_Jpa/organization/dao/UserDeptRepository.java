package com.learn.SpringBoot_Jpa.organization.dao;

import com.learn.SpringBoot_Jpa.organization.pojo.MultiplePrimaryKey.User_Dept_Key;
import com.learn.SpringBoot_Jpa.organization.pojo.relation.User_Dept_Relation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeptRepository extends CrudRepository<User_Dept_Relation, User_Dept_Key> {
     void deleteByDeptId( String deptId);
}
