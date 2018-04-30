package by.home.museum.controller;

import by.home.museum.entity.GuideEntity;
import by.home.museum.entity.UsersEntity;
import by.home.museum.service.GuideService;
import by.home.museum.service.impl.RolesService;
import by.home.museum.service.impl.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/guide")
public class GuideController {

    @Autowired
    GuideService guideService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private SignupService signupService;

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guides
     * HTTP method: GET
     */
    @RequestMapping(value = "/guides", method = RequestMethod.GET)
    public ResponseEntity<?> getGuides() {
        Iterable<GuideEntity> guideList = guideService.findAll();
        return new ResponseEntity<>(guideList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guides/{guideId}
     * HTTP method: GET
     */
    @RequestMapping(value = "/guides/{guideId}", method = RequestMethod.GET)
    public ResponseEntity<?> getGuide(@PathVariable long guideId) {
        GuideEntity guide = guideService.findOne(guideId);
        return new ResponseEntity<>(guide, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guide/guides/add
     * HTTP method: POST
     */
    @RequestMapping(value = "/guides/add", method = RequestMethod.POST)
    public ResponseEntity<?> addGuide(@RequestBody GuideEntity guide) {
        GuideEntity newGuide = guideService.save(guide);
        UsersEntity newAdmin = new UsersEntity(guide.getUsername(), guide.getPassword());
        newAdmin.setRoles(Arrays.asList(rolesService.getByName("ADMIN")));
        signupService.addUser(newAdmin);
        return new ResponseEntity<>(newGuide, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port//guides/update/guideId
     * HTTP method: POST
     */
    @RequestMapping(value = "/guides/update/{guideId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateGuide(@PathVariable long guideId,
                                        @RequestBody GuideEntity guide) {
        GuideEntity updatedGuide = guideService.save(guide);
        return new ResponseEntity<>(updatedGuide, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guides/guideId
     * HTTP method: POST
     */
    @RequestMapping(value = "/guides/delete/{guideId}", method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteGuide(@PathVariable long guideId) {
        GuideEntity guide = guideService.findOne(guideId);
        guideService.delete(guide);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}