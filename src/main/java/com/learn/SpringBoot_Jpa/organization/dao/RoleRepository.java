package com.learn.SpringBoot_Jpa.organization.dao;

import com.learn.SpringBoot_Jpa.organization.pojo.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,String> {

}
