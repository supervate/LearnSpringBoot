package com.learn.makeTestData.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "UserInfo")
public class UserInfoDO {
    @Id
    private String userId;
    private String nickName;
    private Integer age;
    private String sex;
    private String address;
    private String email;

    public UserInfoDO(String userId, String nickName, Integer age, String sex, String address, String email) {
        this.userId = userId;
        this.nickName = nickName;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.email = email;
    }
    public UserInfoDO(){}
}
