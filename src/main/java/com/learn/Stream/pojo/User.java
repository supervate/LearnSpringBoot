package com.learn.Stream.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class User {
    private String name;
    private String age;
    private String nickname;
    private Map<String,User> friends;
}
