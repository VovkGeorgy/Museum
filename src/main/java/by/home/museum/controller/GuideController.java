package by.home.museum.controller;

import by.home.museum.entity.GuideEntity;
import by.home.museum.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guide")
public class GuideController {

    @Autowired
    GuideService guideService;

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guides
     * HTTP method: GET
     */
    @RequestMapping(value = "/guides", method = RequestMethod.GET)
    public ResponseEntity<?> getTours() {
        Iterable<GuideEntity> guideList = guideService.findAll();
        return new ResponseEntity<>(guideList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guides/{guideId}
     * HTTP method: GET
     */
    @RequestMapping(value = "/guides/{guideId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable long guideId) {
        GuideEntity guide = guideService.findOne(guideId);
        return new ResponseEntity<>(guide, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guides
     * HTTP method: POST
     */
    @RequestMapping(value = "/guides/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTour(@RequestBody GuideEntity guide) {
        GuideEntity newTour = guideService.save(guide);
        return new ResponseEntity<>(newTour, HttpStatus.CREATED);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port//guides/update/guideId
     * HTTP method: POST
     */
    @RequestMapping(value = "/guides/update/{guideId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateTour(@PathVariable long guideId,
                                        @RequestBody GuideEntity guide) {
        GuideEntity updatedCustomer = guideService.save(guide);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guides/guideId
     * HTTP method: POST
     */
    @RequestMapping(value = "/guides/delete/{guideId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCustomer(@PathVariable long guideId) {
        GuideEntity guide = guideService.findOne(guideId);
        guideService.delete(guide);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}