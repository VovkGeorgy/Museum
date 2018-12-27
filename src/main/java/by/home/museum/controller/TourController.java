package by.home.museum.controller;

import by.home.museum.entity.ExhibitEntity;
import by.home.museum.entity.TourEntity;
import by.home.museum.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping("/tour")
public class TourController {

    @Autowired
    private TourService tourService;

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(TourController.class);

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours
     * HTTP method: GET
     *
     * @return List of all tours
     */
    @RequestMapping(value = "/tours", method = RequestMethod.GET)
    public ResponseEntity<?> getTours() {
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        Iterable<TourEntity> tourList = tourService.findAll();
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{tourList}, Locale.getDefault()));
        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours/{tourId}
     * HTTP method: GET
     *
     * @param tourId - id of needed tour
     * @return tour by Id
     */
    @RequestMapping(value = "/tours/{tourId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTour(@PathVariable long tourId) {
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourId}, Locale.getDefault()));
        TourEntity tour = tourService.findOne(tourId);
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{tour}, Locale.getDefault()));
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours/exhibits/{tourId}
     * HTTP method: GET
     *
     * @param tourId - id of needed tour
     * @return exhibit List by tour Id
     */
    @RequestMapping(value = "/exhibits/{tourId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourExhibits(@PathVariable long tourId) {
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourId}, Locale.getDefault()));
        TourEntity tour = tourService.findOne(tourId);
        Set<ExhibitEntity> exhibitList = tour.getExhibitEntityList();
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{exhibitList}, Locale.getDefault()));
        return new ResponseEntity<>(exhibitList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours/add
     * HTTP method: POST
     *
     * @param tour - entity to save
     * @return saved tour
     */
    @RequestMapping(value = "/tours/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTour(@RequestBody TourEntity tour) {
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{tour}, Locale.getDefault()));
        TourEntity newTour = tourService.save(tour);
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{newTour}, Locale.getDefault()));
        return new ResponseEntity<>(newTour, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours/update/{tourId}
     * HTTP method: POST
     *
     * @param tour   - entity to update
     * @param tourId - id of needed tour
     * @return updated tour
     */
    @RequestMapping(value = "/tours/update/{tourId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateTour(@PathVariable long tourId,
                                        @RequestBody TourEntity tour) {
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{tour}, Locale.getDefault()));
        TourEntity updatedTour = tourService.save(tour);
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{updatedTour}, Locale.getDefault()));
        return new ResponseEntity<>(updatedTour, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours/delete/{tourId}
     * HTTP method: POST
     *
     * @param tourId - Id of tour which need delete
     */
    @RequestMapping(value = "/tours/delete/{tourId}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteTour(@PathVariable long tourId) {
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourId}, Locale.getDefault()));
        TourEntity tour = tourService.findOne(tourId);
        tourService.delete(tour);
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}