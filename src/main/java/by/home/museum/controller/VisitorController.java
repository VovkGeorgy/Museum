package by.home.museum.controller;

import by.home.museum.entity.UsersEntity;
import by.home.museum.entity.VisitorEntity;
import by.home.museum.service.UserService;
import by.home.museum.service.VisitorService;
import by.home.museum.service.impl.RolesService;
import by.home.museum.service.impl.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    VisitorService visitorService;


    @Autowired
    UserService userService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private SignupService signupService;

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitors
     * HTTP method: GET
     */
    @RequestMapping(value = "/visitors", method = RequestMethod.GET)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getVisitors() {
        Iterable<VisitorEntity> visitorList = visitorService.findAll();
        return new ResponseEntity<>(visitorList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitors/{visitorId}
     * HTTP method: GET
     */
    @RequestMapping(value = "/visitors/{visitorId}", method = RequestMethod.GET)
    public ResponseEntity<?> getVisitor(@PathVariable long visitorId) {
        VisitorEntity visitor = visitorService.findOne(visitorId);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitors
     * HTTP method: POST
     */
    @RequestMapping(value = "/visitors/add", method = RequestMethod.POST)
    public ResponseEntity<?> addVisitor(@RequestBody VisitorEntity visitor) {
        VisitorEntity newVisitor = visitorService.save(visitor);
        UsersEntity newUser = new UsersEntity(visitor.getUsername(), visitor.getPassword());
        newUser.setRoles(Arrays.asList(rolesService.getByName("USER")));
        signupService.addUser(newUser);
        return new ResponseEntity<>(newVisitor, HttpStatus.CREATED);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers/customerId
     * HTTP method: PUT
     */
    @RequestMapping(value = "/visitors/update/{visitorId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateVisitor(@PathVariable long visitorId,
                                           @RequestBody VisitorEntity visitor) {
        VisitorEntity updatedVisitor = visitorService.save(visitor);
        UsersEntity usersEntity = userService.findByUsername(updatedVisitor.getUsername());
        usersEntity.setPassword(updatedVisitor.getPassword());
        signupService.addUser(usersEntity);
        return new ResponseEntity<>(updatedVisitor, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers/customerId
     * HTTP method: DELETE
     */
    @RequestMapping(value = "/visitors/delete/{visitorId}", method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteVisitor(@PathVariable long visitorId) {
        VisitorEntity visitor = visitorService.findOne(visitorId);
        visitorService.delete(visitor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/visitors/getByUsername", method = RequestMethod.POST)
    public ResponseEntity<?> getVisitorByUsername(@RequestBody String username) {
        VisitorEntity neededVisitor = visitorService.findByUsername(username);
        return new ResponseEntity<>(neededVisitor, HttpStatus.CREATED);
    }
}

