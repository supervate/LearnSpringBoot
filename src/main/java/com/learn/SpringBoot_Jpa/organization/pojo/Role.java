package com.learn.SpringBoot_Jpa.organization.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
public class Role {
    @Id
    private String id;
    private String name;
    public Role(){}
    public Role(String id,String name){
        this.name = name;
    }
}
