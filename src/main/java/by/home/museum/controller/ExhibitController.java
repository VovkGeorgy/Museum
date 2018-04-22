package by.home.museum.controller;

import by.home.museum.entity.ExhibitEntity;
import by.home.museum.service.ExhibitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exhibit")
public class ExhibitController {

    @Autowired
    ExhibitService exhibitService;

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibits
     * HTTP method: GET
     */
    @RequestMapping(value = "/exhibits", method = RequestMethod.GET)
    public ResponseEntity<?> getExhibits() {
        Iterable<ExhibitEntity> exhibitList = exhibitService.findAll();
        return new ResponseEntity<>(exhibitList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibits/{exhibitId}
     * HTTP method: GET
     */
    @RequestMapping(value = "/exhibits/{exhibitId}", method = RequestMethod.GET)
    public ResponseEntity<?> getExhibit(@PathVariable long exhibitId) {
        ExhibitEntity exhibit = exhibitService.findOne(exhibitId);
        return new ResponseEntity<>(exhibit, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibits
     * HTTP method: POST
     */
    @RequestMapping(value = "/exhibits/add", method = RequestMethod.POST)
    public ResponseEntity<?> addExhibit(@RequestBody ExhibitEntity exhibit) {
        ExhibitEntity newExhibit = exhibitService.save(exhibit);
        return new ResponseEntity<>(newExhibit, HttpStatus.CREATED);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers/customerId
     * HTTP method: PUT
     */
    @RequestMapping(value = "/exhibits/update/{exhibitId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateExhibit(@PathVariable long exhibitId,
                                        @RequestBody ExhibitEntity exhibit) {
        ExhibitEntity updatedCustomer = exhibitService.save(exhibit);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers/customerId
     * HTTP method: DELETE
     */
    @RequestMapping(value = "/exhibits/delete/{exhibitId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteExhibit(@PathVariable long exhibitId) {
        ExhibitEntity exhibit = exhibitService.findOne(exhibitId);
        exhibitService.delete(exhibit);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
