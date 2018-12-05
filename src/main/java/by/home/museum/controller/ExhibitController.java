package by.home.museum.controller;

import by.home.museum.entity.ExhibitEntity;
import by.home.museum.entity.TourExhibitEntity;
import by.home.museum.service.ExhibitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Locale;

@RestController
@RequestMapping("/exhibit")
public class ExhibitController {

    @Autowired
    private ExhibitService exhibitService;

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(ExhibitController.class);

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibit/exhibits
     * HTTP method: GET
     *
     * @return exhibitList - all founded exhibits
     */
    @RequestMapping(value = "/exhibits", method = RequestMethod.GET)
    public ResponseEntity<?> getExhibits() {
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        Iterable<ExhibitEntity> exhibitList = exhibitService.findAll();
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{exhibitList}, Locale.getDefault()));
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
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        ExhibitEntity exhibit = exhibitService.findOne(exhibitId);
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{exhibit}, Locale.getDefault()));
        return new ResponseEntity<>(exhibit, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibit/tours/{exhibitId}
     * HTTP method: GET
     *
     * @return toursList - all tours of current exhibit
     */
    @RequestMapping(value = "/tours/{exhibitId}", method = RequestMethod.GET)
    public ResponseEntity<?> getExhibitTours(@PathVariable long exhibitId) {
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        ExhibitEntity exhibit = exhibitService.findOne(exhibitId);
        Collection<TourExhibitEntity> toursList = exhibit.getTourExhibitsByExhibitId();
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{toursList}, Locale.getDefault()));
        return new ResponseEntity<>(toursList, HttpStatus.OK);
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
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{exhibit}, Locale.getDefault()));
        ExhibitEntity newExhibit = exhibitService.save(exhibit);
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{newExhibit}, Locale.getDefault()));
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
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{exhibit}, Locale.getDefault()));
        ExhibitEntity updatedExhibit = exhibitService.save(exhibit);
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{updatedExhibit}, Locale.getDefault()));
        return new ResponseEntity<>(updatedExhibit, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/exhibit/exhibits/delete/{exhibitId}
     * HTTP method: POST
     * @param exhibitId - Id of exhibit which need delete
     */
    @RequestMapping(value = "/exhibits/delete/{exhibitId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteExhibit(@PathVariable long exhibitId) {
        logger.debug(messageSource.getMessage("controller.getRequest", new Object[]{exhibitId}, Locale.getDefault()));
        ExhibitEntity exhibit = exhibitService.findOne(exhibitId);
        exhibitService.delete(exhibit);
        logger.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
