package com.learn.SpringBoot_MongoDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource()
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    List<Customer> findByFirstName(@Param("name") String firstName);

    List<Customer> findByLastName(@Param("name")String lastName);

}