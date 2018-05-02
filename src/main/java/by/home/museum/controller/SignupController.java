package by.home.museum.controller;

import by.home.museum.entity.UsersEntity;
import by.home.museum.service.RolesService;
import by.home.museum.service.SignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Locale;

@RestController
public class SignupController {

    @Autowired
    private SignupService signupService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/signup
     * HTTP method: POST
     *
     * @param user - new user
     * @return - status 200-OK
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody UsersEntity user) {
        logger.info(messageSource.getMessage("controller.getRequest", new Object[]{user}, Locale.getDefault()));
        user.setRoles(Arrays.asList(rolesService.getByName("USER")));
        UsersEntity newUser = signupService.addUser(user);
        logger.info(messageSource.getMessage("controller.returnResponse", new Object[]{newUser}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/delUser
     * HTTP method: POST
     *
     * @param user - user which need delete
     * @return - status 200-OK
     */
    @RequestMapping(value = "/delUser", method = RequestMethod.POST)
    public ResponseEntity<?> delUser(@RequestBody UsersEntity user) {
        logger.info(messageSource.getMessage("controller.getRequest", new Object[]{user}, Locale.getDefault()));
        signupService.delUser(user);
        logger.info(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/addAdmin
     * HTTP method: POST
     *
     * @param user - user which need to create of role ADMIN
     * @return - status 200-OK
     */
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addAdmin(@RequestBody UsersEntity user) {
        logger.info(messageSource.getMessage("controller.getRequest", new Object[]{user}, Locale.getDefault()));
        user.setRoles(Arrays.asList(rolesService.getByName("ADMIN"), rolesService.getByName("USER")));
        UsersEntity newUser = signupService.addUser(user);
        logger.info(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
