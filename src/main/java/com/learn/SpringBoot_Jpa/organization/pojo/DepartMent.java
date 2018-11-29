package com.learn.SpringBoot_Jpa.organization.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
public class DepartMent {
    @Id
    @GeneratedValue
    private String code;

    private String name;
    private String parentCode;
    public  DepartMent(){}
    public DepartMent(String name,String parentCode){
        this.name = name;
        this.parentCode = parentCode;
    }
}
