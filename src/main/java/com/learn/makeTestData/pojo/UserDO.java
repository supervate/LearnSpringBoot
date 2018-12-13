package com.learn.makeTestData.pojo;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "User")
public class UserDO {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    private Integer id;

    private String userName;

    private String password;

    public UserDO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserDO() {}
}
