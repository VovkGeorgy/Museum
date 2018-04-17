package by.home.museum.controller;

import by.home.museum.entity.TourEntity;
import by.home.museum.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tour")
public class TourController {

    @Autowired
    TourService tourService;

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tours
     * HTTP method: GET
     */
    @RequestMapping(value = "/tours", method = RequestMethod.GET)
    public ResponseEntity<?> getTours() {
        Iterable<TourEntity> tourList = tourService.findAll();
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers/{customerId}
     * HTTP method: GET
     */
    @RequestMapping(value = "/tours/{tourId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable long tourId) {
        TourEntity tour = tourService.findOne(tourId);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tours
     * HTTP method: POST
     */
    @RequestMapping(value = "/tours/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTour(@RequestBody TourEntity tour) {
        TourEntity newTour = tourService.save(tour);
        return new ResponseEntity<>(newTour, HttpStatus.CREATED);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers/customerId
     * HTTP method: PUT
     */
    @RequestMapping(value = "/tours/update/{tourId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateTour(@PathVariable long tourId,
                                        @RequestBody TourEntity tour) {
        TourEntity updatedCustomer = tourService.save(tour);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers/customerId
     * HTTP method: DELETE
     */
    @RequestMapping(value = "/tours/delete/{tourId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCustomer(@PathVariable long tourId) {
        TourEntity tour = tourService.findOne(tourId);
        tourService.delete(tour);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}