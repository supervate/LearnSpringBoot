package com.learn.SpringBoot_Jpa.organization.pojo.relation;

import com.learn.SpringBoot_Jpa.organization.pojo.MultiplePrimaryKey.User_Role_Key;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@IdClass(User_Role_Key.class)
@Data
@Entity
public class User_Role_Relation{
    @Id
    private String userId;
    @Id
    private String roleId;

    private String userName;
    private String roleName;

    public User_Role_Relation(String userId,String roleId){}
    public User_Role_Relation(){}
}
