package by.home.museum.controller;

import by.home.museum.entity.ExhibitEntity;
import by.home.museum.entity.GuideEntity;
import by.home.museum.entity.TourEntity;
import by.home.museum.entity.VisitorEntity;
import by.home.museum.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Tour component rest controller
 */
@Slf4j
@RestController
@RequestMapping("/api/tour")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TourController {

    private final TourService tourService;
    private final MessageSource messageSource;

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours
     * HTTP method: GET
     *
     * @return List of all tours
     */
    @RequestMapping(value = "/tours", method = RequestMethod.GET)
    public ResponseEntity<?> getTours() {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        Iterable<TourEntity> tourList = tourService.findAll();
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{tourList}, Locale.getDefault()));
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
    @RequestMapping(value = "/{tourId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTour(@PathVariable long tourId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourId}, Locale.getDefault()));
        TourEntity tour = tourService.findOne(tourId);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{tour}, Locale.getDefault()));
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
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourId}, Locale.getDefault()));
        TourEntity tour = tourService.findOne(tourId);
        Set<ExhibitEntity> exhibitList = new HashSet<>(tour.getExhibitEntityList());
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{exhibitList}, Locale.getDefault()));
        return new ResponseEntity<>(exhibitList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours/guide/{tourId}
     * HTTP method: GET
     *
     * @param tourId tour id
     * @return tour guide
     */
    @RequestMapping(value = "/guide/{tourId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourGuide(@PathVariable long tourId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourId}, Locale.getDefault()));
        TourEntity tour = tourService.findOne(tourId);
        GuideEntity guide = tour.getGuideEntity();
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{guide}, Locale.getDefault()));
        return new ResponseEntity<>(guide, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours/add
     * HTTP method: POST
     *
     * @param tour - entity to save
     * @return saved tour
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTour(@RequestBody TourEntity tour) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tour}, Locale.getDefault()));
        TourEntity newTour = tourService.save(tour);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{newTour}, Locale.getDefault()));
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
    @RequestMapping(value = "/update/{tourId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateTour(@PathVariable long tourId,
                                        @RequestBody TourEntity tour) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourId}, Locale.getDefault()));
        TourEntity tourEntity = tourService.findOne(tourId);
        tourEntity.setTheme(tour.getTheme());
        tourEntity.setTypeOfExhibits(tour.getTypeOfExhibits());
        tourEntity.setDuration(tour.getDuration());
        tourEntity.setCost(tour.getCost());
        tourEntity.setImageUrl(tour.getImageUrl());
        TourEntity savedEntity = tourService.save(tourEntity);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{savedEntity}, Locale.getDefault()));
        return new ResponseEntity<>(tourEntity, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours/delete/{tourId}
     * HTTP method: POST
     *
     * @param tourId - Id of tour which need delete
     */
    @RequestMapping(value = "/delete/{tourId}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteTour(@PathVariable long tourId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourId}, Locale.getDefault()));
        TourEntity tour = tourService.findOne(tourId);
        tourService.delete(tour);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/tour/tours/visitors/{tourId}
     * HTTP method: GET
     *
     * @param tourId - Id of tour which need delete
     */
    @RequestMapping(value = "/visitors/{tourId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourVisitors(@PathVariable long tourId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourId}, Locale.getDefault()));
        Set<VisitorEntity> visitorsSet = tourService.findOne(tourId).getVisitorEntitySet();
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(visitorsSet, HttpStatus.OK);
    }

    /**
     * Get tours without guides
     *
     * @return list of tours
     */
    @RequestMapping(value = "/tours/withoutGuide", method = RequestMethod.GET)
    public ResponseEntity<?> getTourVisitors() {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{}, Locale.getDefault()));
        List<TourEntity> toursWithoutGuide = tourService.getAllByGuideEntityIsNull();
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(toursWithoutGuide, HttpStatus.OK);
    }
}
