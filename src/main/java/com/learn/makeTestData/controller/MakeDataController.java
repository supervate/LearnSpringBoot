package com.learn.makeTestData.controller;

import com.learn.makeTestData.MakeDataUtils;
import com.learn.makeTestData.dao.UserInfoRepository;
import com.learn.makeTestData.dao.UserRepository;
import com.learn.makeTestData.pojo.UserDO;
import com.learn.makeTestData.pojo.UserInfoDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class MakeDataController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserInfoRepository userInfoRepository;
    @GetMapping("makeData/users/{count}")
    public String makeUsers(@PathVariable(name = "count")int count){
        int totals = 0;
        String[] sexs = {"男","女","不男不女"};
        for (int i = 0; i < count; i++) {
            UserDO user = new UserDO();
            user.setUserName(MakeDataUtils.makeEmail(null));
            user.setPassword("123456");
            user = userRepository.save(user);
            UserInfoDO userInfo = new UserInfoDO();
            userInfo.setAddress(MakeDataUtils.makeAddress(null ,null ,null ,null));
            userInfo.setAge(MakeDataUtils.makeAge(100));
            userInfo.setEmail(user.getUserName());
            userInfo.setNickName(MakeDataUtils.makeName(null,null, 0));
            userInfo.setUserId(user.getId());

            userInfo.setSex(MakeDataUtils.makeSex(sexs));
            userInfoRepository.save(userInfo);
            totals++;
            log.info(String.format("插入第%d条数据", totals));
        }
        return totals+"";
    }
}
