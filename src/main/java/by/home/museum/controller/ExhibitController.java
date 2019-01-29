package by.home.museum.controller;

import by.home.museum.entity.ExhibitEntity;
import by.home.museum.entity.TourEntity;
import by.home.museum.entity.TourExhibitDao;
import by.home.museum.service.ExhibitService;
import by.home.museum.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Set;

/**
 * Exhibit component rest controller
 */
@Slf4j
@RestController
@RequestMapping("/exhibit")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExhibitController {

    private final ExhibitService exhibitService;
    private final MessageSource messageSource;
    private final TourService tourService;

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibit/exhibits
     * HTTP method: GET
     *
     * @return exhibitList - all founded exhibits
     */
    @RequestMapping(value = "/exhibits", method = RequestMethod.GET)
    public ResponseEntity<?> getExhibits() {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        Iterable<ExhibitEntity> exhibitList = exhibitService.findAll();
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{exhibitList}, Locale.getDefault()));
        return new ResponseEntity<>(exhibitList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibit/exhibits/{exhibitId}
     * HTTP method: GET
     *
     * @return exhibit of it Id
     */
    @RequestMapping(value = "/exhibits/{exhibitId}", method = RequestMethod.GET)
    public ResponseEntity<?> getExhibit(@PathVariable long exhibitId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        ExhibitEntity exhibit = exhibitService.findOne(exhibitId);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{exhibit}, Locale.getDefault()));
        return new ResponseEntity<>(exhibit, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibit/tours/{exhibitId}
     * HTTP method: GET
     *
     * @return toursList - all tours of current exhibit
     */
    @RequestMapping(value = "/exhibits/tours/{exhibitId}", method = RequestMethod.GET)
    public ResponseEntity<?> getExhibitTours(@PathVariable long exhibitId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        ExhibitEntity exhibit = exhibitService.findOne(exhibitId);
        Set<TourEntity> tourSet = exhibit.getTourEntitySet();
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{exhibit}, Locale.getDefault()));
        return new ResponseEntity<>(tourSet, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibit/exhibits/add
     * HTTP method: POST
     *
     * @param exhibit - exhibit entity
     * @return newExhibit - saved exhibit entity
     */
    @RequestMapping(value = "/exhibits/add", method = RequestMethod.POST)
    public ResponseEntity<?> addExhibit(@RequestBody ExhibitEntity exhibit) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{exhibit}, Locale.getDefault()));
        ExhibitEntity newExhibit = exhibitService.save(exhibit);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{newExhibit}, Locale.getDefault()));
        return new ResponseEntity<>(newExhibit, HttpStatus.CREATED);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibit/exhibits/update/{exhibitId}
     * HTTP method: POST
     *
     * @param exhibit   - exhibit entity to update
     * @param exhibitId - Id of exhibit which need update
     * @return updated exhibit
     */
    @RequestMapping(value = "/exhibits/update/{exhibitId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateExhibit(@PathVariable long exhibitId,
                                           @RequestBody ExhibitEntity exhibit) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{exhibit}, Locale.getDefault()));
        ExhibitEntity updatedExhibit = exhibitService.save(exhibit);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{updatedExhibit}, Locale.getDefault()));
        return new ResponseEntity<>(updatedExhibit, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibit/exhibits/delete/{exhibitId}
     * HTTP method: POST
     *
     * @param exhibitId - Id of exhibit which need delete
     */
    @RequestMapping(value = "/exhibits/delete/{exhibitId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteExhibit(@PathVariable long exhibitId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{exhibitId}, Locale.getDefault()));
        ExhibitEntity exhibit = exhibitService.findOne(exhibitId);
        exhibitService.delete(exhibit);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibit/exhibits/removeTour
     * HTTP method: POST
     *
     * @param ted tour-exhibit-dao entity
     * @return HTTP status OK
     */
    @RequestMapping(value = "/exhibits/removeTour", method = RequestMethod.POST)
    public ResponseEntity<?> removeTourFromExhibit(@RequestBody TourExhibitDao ted) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{ted}, Locale.getDefault()));
        ExhibitEntity exhibitEntity = exhibitService.findOne(ted.getExhibitId());
        Set<TourEntity> exhibitToursSet = exhibitEntity.getTourEntitySet();
        exhibitToursSet.remove(tourService.findOne(ted.getTourId()));
        exhibitEntity.setTourEntitySet(exhibitToursSet);
        ExhibitEntity updatedExhibit = exhibitService.save(exhibitEntity);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{updatedExhibit}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
