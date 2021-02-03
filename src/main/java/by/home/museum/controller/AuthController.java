package by.home.museum.controller;

import by.home.museum.entity.UsersEntity;
import by.home.museum.service.RolesService;
import by.home.museum.service.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

/**
 * SignUp component rest controller
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final SignupService signupService;
    private final RolesService rolesService;
    private final MessageSource messageSource;
    private final TokenStore tokenStore;

    @RequestMapping(value = "/oauth/revoke-token", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
    }

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
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{user}, Locale.getDefault()));
        user.setRoles(Collections.singletonList(rolesService.getByName("VISITOR")));
        UsersEntity newUser = signupService.addUser(user);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{newUser}, Locale.getDefault()));
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
    @PreAuthorize("hasRole('GUIDE')")
    public ResponseEntity<?> delUser(@RequestBody UsersEntity user) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{user}, Locale.getDefault()));
        signupService.delUser(user);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAdmin(@RequestBody UsersEntity user) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{user}, Locale.getDefault()));
        user.setRoles(Arrays.asList(
                rolesService.getByName("ADMIN"),
                rolesService.getByName("GUIDE"),
                rolesService.getByName("VISITOR")
        ));
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
