package com.learn.SpringBoot_Jpa.organization.pojo.relation;

import com.learn.SpringBoot_Jpa.organization.pojo.MultiplePrimaryKey.User_Dept_Key;
import com.learn.SpringBoot_Jpa.organization.pojo.MultiplePrimaryKey.User_Role_Key;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
@Entity
@IdClass(User_Dept_Key.class)
@Data
public class User_Dept_Relation {
    @Id
    private String userId;
    @Id
    private String deptId;

    private String userName;
    private String deptName;

    public User_Dept_Relation(String userId,String deptId){
        this.userId = userId;
        this.deptId = deptId;
    }
    public User_Dept_Relation(){}

}
