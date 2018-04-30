package by.home.museum.controller;

import by.home.museum.entity.ExhibitEntity;
import by.home.museum.entity.GuideEntity;
import by.home.museum.entity.TourEntity;
import by.home.museum.entity.TourExhibitEntity;
import by.home.museum.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
     * URL: http://hostname:port/tours/{tourId}
     * HTTP method: GET
     */
    @RequestMapping(value = "/tours/{tourId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTour(@PathVariable long tourId) {
        TourEntity tour = tourService.findOne(tourId);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tours/exhibits
     * HTTP method: GET
     */
    @RequestMapping(value = "/exhibits/{tourId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourExhibits(@PathVariable long tourId) {
        TourEntity tour = tourService.findOne(tourId);
        Collection<TourExhibitEntity> exhibitList = tour.getTourExhibitsByTourId();
        return new ResponseEntity<>(exhibitList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours/add
     * HTTP method: POST
     */
    @RequestMapping(value = "/tours/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTour(@RequestBody TourEntity tour) {
        TourEntity newTour = tourService.save(tour);
        return new ResponseEntity<>(newTour, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tours/update/{tourId}
     * HTTP method: POST
     */
    @RequestMapping(value = "/tours/update/{tourId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateTour(@PathVariable long tourId,
                                        @RequestBody TourEntity tour) {
        TourEntity updatedTour = tourService.save(tour);
        return new ResponseEntity<>(updatedTour, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tours/delete/{tourId}
     * HTTP method: POST
     */
    @RequestMapping(value = "/tours/delete/{tourId}", method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteTour(@PathVariable long tourId) {
        TourEntity tour = tourService.findOne(tourId);
        tourService.delete(tour);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}