package com.blogApp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogApp.Repository.RoleRespository;
import com.blogApp.models.Role;

@SpringBootApplication
public class BlogApplication2Application implements CommandLineRunner{

	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RoleRespository roleRespository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication2Application.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
//	@Bean
//	public MockMvc mockMvc() {
//	    return new MockMvc();
//	}


	@Override
	public void run(String... args) throws Exception {

		System.out.println(this.encoder.encode("ABC"));
		System.out.println("user story");
		try {
	             Role role = new Role();
	             role.setId(501);
	             role.setName("ROLE_ADMIN");
	             Role role2 = new Role();
	             role2.setId(502);
	             role2.setName("ROLE_NORMAL");
	             List<Role> roles = List.of(role,role2);
	             List<Role> saveAll = roleRespository.saveAll(roles);
		}catch (Exception e) {
			
			e.getMessage();
		}

	}
	
	
}
