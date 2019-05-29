package by.home.museum.controller;

import by.home.museum.entity.TourEntity;
import by.home.museum.entity.TourVisitorDao;
import by.home.museum.entity.UsersEntity;
import by.home.museum.entity.VisitorEntity;
import by.home.museum.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * Visitor component rest controller
 */
@Slf4j
@RestController
@RequestMapping("/api/visitor")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VisitorController {

    private final VisitorService visitorService;
    private final TourService tourService;
    private final UserService userService;
    private final RolesService rolesService;
    private final SignupService signupService;
    private final MessageSource messageSource;

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/visitors
     * HTTP method: GET
     *
     * @return List of all visitors
     */
    @RequestMapping(value = "/visitors", method = RequestMethod.GET)
    public ResponseEntity<?> getVisitors() {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        Iterable<VisitorEntity> visitorList = visitorService.findAll();
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{visitorList}, Locale.getDefault()));
        return new ResponseEntity<>(visitorList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/visitors/{visitorId}
     * HTTP method: GET
     *
     * @param visitorId - id of needed visitor
     * @return visitor by Id
     */
    @RequestMapping(value = "/{visitorId}", method = RequestMethod.GET)
    public ResponseEntity<?> getVisitor(@PathVariable long visitorId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitorId}, Locale.getDefault()));
        VisitorEntity visitor = visitorService.findOne(visitorId);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{visitor}, Locale.getDefault()));
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/visitors/add
     * HTTP method: POST
     * Method add a visitor as a entity, and as a USER
     *
     * @param visitor - entity to save
     * @return saved visitor
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addVisitor(@RequestBody VisitorEntity visitor) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitor}, Locale.getDefault()));
        VisitorEntity newVisitor = visitorService.save(visitor);
        UsersEntity newUser = new UsersEntity(visitor.getUsername(), visitor.getPassword(), Collections.singletonList
                (rolesService.getByName("VISITOR")));
        signupService.addUser(newUser);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{newVisitor}, Locale.getDefault()));
        return new ResponseEntity<>(newVisitor, HttpStatus.CREATED);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/visitors/update/{visitorId}
     * HTTP method: POST
     * Method update a visitor as a entity, and as a USER
     *
     * @param visitor - entity to update
     * @return updated visitor
     */
    @RequestMapping(value = "/update/{visitorId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateVisitor(@PathVariable long visitorId,
                                           @RequestBody VisitorEntity visitor) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitor}, Locale.getDefault()));
        VisitorEntity updatedVisitor = visitorService.save(visitor);
        UsersEntity usersEntity = userService.findByUsername(updatedVisitor.getUsername());
        if (usersEntity == null) {
            UsersEntity newUser = new UsersEntity(visitor.getUsername(), visitor.getPassword(), Collections.singletonList
                    (rolesService.getByName("VISITOR")));
            signupService.addUser(newUser);
        } else {
            usersEntity.setPassword(updatedVisitor.getPassword());
            signupService.addUser(usersEntity);
        }
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{updatedVisitor}, Locale.getDefault()));
        return new ResponseEntity<>(updatedVisitor, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/visitors/delete/{visitorId}
     * HTTP method: POST
     *
     * @param visitorId - Id of visitor which need delete
     */
    @RequestMapping(value = "/delete/{visitorId}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteVisitor(@PathVariable long visitorId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitorId}, Locale.getDefault()));
        VisitorEntity visitor = visitorService.findOne(visitorId);
        visitor.setTourEntitySet(new HashSet<>());
        visitorService.save(visitor);
        visitorService.delete(visitor);
        signupService.delUser(userService.findByUsername(visitor.getUsername()));
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/visitors/getByUsername
     * HTTP method: POST
     *
     * @param username - username of visitor which need get
     * @return visitor by username
     */
    @RequestMapping(value = "/getByUsername", method = RequestMethod.POST)
    public ResponseEntity<?> getVisitorByUsername(@RequestBody String username) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{username}, Locale.getDefault()));
        VisitorEntity neededVisitor = visitorService.findByUsername(username);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{neededVisitor}, Locale.getDefault()));
        return new ResponseEntity<>(neededVisitor, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/visitors/addTour
     * HTTP method: POST
     *
     * @param tvd tour-visitor-dao entity
     * @return updated visitor
     */
    @RequestMapping(value = "/addTour", method = RequestMethod.POST)
    public ResponseEntity<?> addTourToVisitor(@RequestBody TourVisitorDao tvd) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tvd.getVisitorId()}, Locale.getDefault()));
        VisitorEntity visitorEntity = visitorService.findOne(tvd.getVisitorId());
        Set<TourEntity> set = visitorEntity.getTourEntitySet();
        set.add(tourService.findOne(tvd.getTourId()));
        VisitorEntity updatedVisitor = visitorService.save(visitorEntity);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{updatedVisitor}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/toursCheck
     * HTTP method: POST
     *
     * @param tVd tour-visitor-dao entity
     * @return result of checking
     */
    @RequestMapping(value = "/toursCheck", method = RequestMethod.POST)
    public ResponseEntity<?> isTourInFavourites(@RequestBody TourVisitorDao tVd) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tVd}, Locale.getDefault()));
        VisitorEntity visitor = visitorService.findOne(tVd.getVisitorId());
        Set<TourEntity> toursSet = visitor.getTourEntitySet();
        TourEntity tourEntity = tourService.findOne(tVd.getTourId());
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{toursSet}, Locale.getDefault()));
        return new ResponseEntity<>(toursSet.contains(tourEntity), HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/visitors/removeTour
     * HTTP method: POST
     *
     * @param tVd tour-visitor-dao entity
     * @return HTTP status OK
     */
    @RequestMapping(value = "/removeTour", method = RequestMethod.POST)
    public ResponseEntity<?> removeTourFromVisitor(@RequestBody TourVisitorDao tVd) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tVd}, Locale.getDefault()));
        VisitorEntity visitorEntity = visitorService.findOne(tVd.getVisitorId());
        Set<TourEntity> visitorToursSet = visitorEntity.getTourEntitySet();
        visitorToursSet.remove(tourService.findOne(tVd.getTourId()));
        visitorEntity.setTourEntitySet(visitorToursSet);
        VisitorEntity updatedVisitor = visitorService.save(visitorEntity);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{updatedVisitor}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/tours/findAll/
     * HTTP method: GET
     *
     * @param visitorId id of visitor
     * @return tours set from visitor
     */
    @RequestMapping(value = "/tours/{visitorId}", method = RequestMethod.GET)
    public ResponseEntity<?> getVisitorTours(@PathVariable long visitorId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitorId}, Locale.getDefault()));
        VisitorEntity visitor = visitorService.findOne(visitorId);
        Set<TourEntity> toursSet = visitor.getTourEntitySet();
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{toursSet}, Locale.getDefault()));
        return new ResponseEntity<>(toursSet, HttpStatus.OK);
    }
}

