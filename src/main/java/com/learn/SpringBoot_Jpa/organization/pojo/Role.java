package com.learn.SpringBoot_Jpa;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Table
@Data
public class Role {
    @Id
    String id;
    String name;
}
