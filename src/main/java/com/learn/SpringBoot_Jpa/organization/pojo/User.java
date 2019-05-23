package com.learn.SpringBoot_Jpa.organization.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    private String id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String nickName;
    private String phone;
    public User(){}
    public User(String username,String password){
        this.username = username;
        this.password = password;
    }
}
