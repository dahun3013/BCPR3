package com.bcpr.backend;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bcpr.backend.security.domain.Role;
import com.bcpr.backend.security.domain.User;
import com.bcpr.backend.security.service.UserService;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
    CommandLineRunner run(UserService userService){
        return args -> {

            //userService.saveRole(new Role(null,"ROLE_USER"));
            //userService.saveUser(new User(null,"dahun3013@gmail.com","DMTT","",new ArrayList<>()));
            //userService.addRoleToUser("dahun3013@gmail.com","ROLE_USER");

        };
    }
}
