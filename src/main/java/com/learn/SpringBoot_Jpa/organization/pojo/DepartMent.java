package com.learn.SpringBoot_Jpa;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.List;

@Table
@Data
public class DepartMent {
    @Id
    @GeneratedValue
    String code;
    String name;
    String parentCode;
    List<Role> roles;
}
