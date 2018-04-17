package by.home.museum.controller;

import by.home.museum.entity.UsersEntity;
import by.home.museum.service.impl.RolesService;
import by.home.museum.service.impl.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class SignupController {
	
	@Autowired
	private SignupService signupService;

	@Autowired
	private RolesService rolesService;

    /**
     * 
     * user signup
     * @param user
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody UsersEntity user) {
//   		user.setRoles(Arrays.asList(new RolesEntity("USER")));
   		user.setRoles(Arrays.asList(rolesService.getByName("USER")));
		UsersEntity newUser = signupService.addUser(user);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
