package com.learn.SpringBoot_Jpa.organization.dao;

import com.learn.SpringBoot_Jpa.organization.pojo.MultiplePrimaryKey.User_Role_Key;
import com.learn.SpringBoot_Jpa.organization.pojo.relation.User_Role_Relation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<User_Role_Relation, User_Role_Key> {

}
