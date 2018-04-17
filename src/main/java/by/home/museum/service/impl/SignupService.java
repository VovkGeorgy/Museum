package by.home.museum.service.impl;

import by.home.museum.entity.RolesEntity;
import by.home.museum.entity.UsersEntity;
import by.home.museum.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@Transactional
public class SignupService {
	
	@Autowired
	private UsersRepository userRepository;

	@Autowired
    PasswordEncoder passwordEncoder;
	
	public UsersEntity addUser(UsersEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	/**
	 * 
	 * set up a default customer with two roles USER and ADMIN
	 * 
	 */
	@PostConstruct
	private void setupDefaultUser() {
		//-- just to make sure there is an ADMIN user exist in the database for testing purpose
			if (userRepository.count() == 0) {
				userRepository.save(new UsersEntity("admin",
										passwordEncoder.encode("11111"),
										Arrays.asList(new RolesEntity("USER"), new RolesEntity("ADMIN"))));
			}
	}
	
}
