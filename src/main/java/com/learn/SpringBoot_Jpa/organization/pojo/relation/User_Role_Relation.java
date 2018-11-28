package com.learn.SpringBoot_Jpa.organization.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Table
@IdClass(User_Role_Key.class)
@Data
public class User_Role_Relation{
    @Id
    private String userId;
    @Id
    private String roleId;
}
