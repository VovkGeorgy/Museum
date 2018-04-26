package by.home.museum.controller;

import by.home.museum.entity.RolesEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/abo")
public class AboutController {
    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/about
     * HTTP method: GET
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ResponseEntity<?> home() {
        return new ResponseEntity<>("This is the about page for Museum application.", HttpStatus.OK);
    }

    @RequestMapping(value = "/whoiam", method = RequestMethod.GET)
    public ResponseEntity<?> getRole() {

        /**
         * Obtaining information about the current user
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String temp = ("" + authentication.getAuthorities());

        //CrmUserDetails principal = (CrmUserDetails) authentication.getPrincipal();
        //System.out.println("logged in user name:: " + principal.getUsername());

        return new ResponseEntity<>(authentication.getAuthorities(), HttpStatus.OK);
    }

    @RequestMapping(value = "/protected", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> protect() {
        return new ResponseEntity<>("This is ADMIN PAGE.", HttpStatus.OK);
    }
}
