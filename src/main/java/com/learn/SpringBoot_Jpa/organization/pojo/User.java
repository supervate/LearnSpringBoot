package com.learn.SpringBoot_Jpa;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@Table
public class User {
    @Id
    @GeneratedValue
    String id;
    String departMent;
    List<String> roleIds;
}
