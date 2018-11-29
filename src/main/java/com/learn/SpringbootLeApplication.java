package com.learn;

import com.learn.SpringBoot_Jpa.organization.dao.RoleRepository;
import com.learn.SpringBoot_Jpa.organization.dao.UserDeptRepository;
import com.learn.SpringBoot_Jpa.organization.dao.UserRepository;
import com.learn.SpringBoot_Jpa.organization.dao.UserRoleRepository;
import com.learn.SpringBoot_Jpa.organization.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableWebSocket
@EnableJpaRepositories(basePackages = {"com.learn.SpringBoot_Jpa"})
public class SpringbootLeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLeApplication.class, args);
	}

//	@Autowired
//	private CustomerRepository repository;
//	@Autowired
//	MongoClient mongoClient;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserDeptRepository userDeptRepository;
	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("a","123456"));
		userRepository.save(new User("b","123456"));
		userRepository.save(new User("c","123456"));
		userRepository.save(new User("a","123456"));
	}

	/**
	 * 会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
	 * 要注意，如果使用独立的servlet容器，
	 * 而不是直接使用springboot的内置容器，
	 * 就不要注入ServerEndpointExporter，因为它将由容器自己提供和管理。
	 */
//	@Bean
//	public ServerEndpointExporter serverEndpointExporter() {
//		return new ServerEndpointExporter();
//	}
}
