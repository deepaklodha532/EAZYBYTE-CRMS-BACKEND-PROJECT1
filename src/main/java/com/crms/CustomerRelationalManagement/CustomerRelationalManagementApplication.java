package com.crms.CustomerRelationalManagement;

import com.crms.CustomerRelationalManagement.enities.Role;
import com.crms.CustomerRelationalManagement.enities.User;
import com.crms.CustomerRelationalManagement.repositories.RoleRepository;
import com.crms.CustomerRelationalManagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class CustomerRelationalManagementApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRelationalManagementApplication.class, args);
	}

	@Autowired
	RoleRepository roleRepository ;

	@Autowired
	UserRepository userRepository ;

	@Autowired
	PasswordEncoder passwordEncoder ;


	@Override
	public void run(String... args) throws Exception {
//		Role role1 = new Role();
//		role1.setRoleName(AppConstant.ROLE_ADMIN);
//		role1.setRoleId(UUID.randomUUID().toString());
//
//
//		Role role2 = new Role();
//		role2.setRoleName(AppConstant.ROLE_GUST);
//		role2.setRoleId(UUID.randomUUID().toString());



		Role roleNormal =  roleRepository.findByRoleName("ROLE_NORMAL").orElse(null) ;

		if (roleNormal ==null){
			Role role2  = new Role() ;
			role2.setRoleId(UUID.randomUUID().toString());
			role2.setRoleName("ROLE_NORMAL");
			roleRepository.save(role2) ;
		}


		Role roleAdmin =roleRepository.findByRoleName("ROLE_ADMIN").orElse(null) ;

		if(roleAdmin==null) {
			Role role1 = new Role();
			role1.setRoleId(UUID.randomUUID().toString());
			role1.setRoleName("ROLE_ADMIN");
			roleRepository.save(role1) ;
		}

//		ek admin user create karenge
		User user = userRepository.findByEmail("admin@gmail.com").orElse(null) ;
		if (user == null ){
			user =new User() ;
			user.setName("admin");
			user.setEmail("admin@gmail.com");
			user.setPassword(passwordEncoder.encode("admin"));
			user.setRoles(List.of(roleNormal));
			user.setUserId(UUID.randomUUID().toString());

			userRepository.save(user) ;
		}

		User user2 = userRepository.findByEmail("deepak@gmail.com").orElse(null) ;
		if (user2 == null ){
			user2 =new User() ;
			user2.setName("deepak");
			user2.setEmail("deepak@gmail.com");
			user2.setPassword(passwordEncoder.encode("deepak"));
			user2.setRoles(List.of(roleAdmin));
			user2.setUserId(UUID.randomUUID().toString());

			userRepository.save(user2) ;
		}
	}
}
