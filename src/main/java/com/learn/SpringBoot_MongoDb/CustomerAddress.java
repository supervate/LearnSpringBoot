package com.learn.SpringBoot_MongoDb;

import com.mongodb.Mongo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
public class CustomerAddress {
    @Id
    private String id;
    @Field()
    private String addressLocation;
}
