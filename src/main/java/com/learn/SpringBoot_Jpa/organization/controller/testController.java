package com.learn.SpringBoot_Jpa.organization.controller;

import com.learn.SpringBoot_Jpa.organization.dao.RoleRepository;
import com.learn.SpringBoot_Jpa.organization.dao.UserDeptRepository;
import com.learn.SpringBoot_Jpa.organization.dao.UserRepository;
import com.learn.SpringBoot_Jpa.organization.dao.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserDeptRepository userDeptRepository;

    @RequestMapping("/test")
    @Transactional
    public Object test(){
        userDeptRepository.deleteByDeptId("b");
        return 0;
    }

}
