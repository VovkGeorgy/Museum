package by.home.museum.security;

import by.home.museum.entity.UsersEntity;
import by.home.museum.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MuseumUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersRepository userRepository;
	
//	@Autowired
//	PasswordEncoder passwordEncoder;
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//    	if (userRepository.count() == 0) {
//    		System.out.println("There is no user exist in the database. So, adding two users");
//    		userRepository.save(new User("crmadmin", passwordEncoder.encode("adminpass"), Arrays.asList(new UserRole("USER"), new UserRole("ADMIN"))));
//    		userRepository.save(new User("crmuser", passwordEncoder.encode("crmpass"), Arrays.asList(new UserRole("USER"))));
//    	}
    	
        UsersEntity user = userRepository.findByUsername(userName);
        if(user == null){
            throw new UsernameNotFoundException("UserName "+userName+" not found");
        }
        return new MuseumUserDetails(user);
    }	

}
