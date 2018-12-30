package by.home.museum.controller;

import by.home.museum.entity.TourEntity;
import by.home.museum.entity.TourVisitorDao;
import by.home.museum.entity.UsersEntity;
import by.home.museum.entity.VisitorEntity;
import by.home.museum.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    private final VisitorService visitorService;
    private final TourService tourService;
    private final UserService userService;
    private final RolesService rolesService;
    private final SignupService signupService;
    private final MessageSource messageSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(VisitorController.class);

    @Autowired
    public VisitorController(VisitorService visitorService, TourService tourService, UserService userService, RolesService rolesService, SignupService signupService, MessageSource messageSource) {
        this.visitorService = visitorService;
        this.tourService = tourService;
        this.userService = userService;
        this.rolesService = rolesService;
        this.signupService = signupService;
        this.messageSource = messageSource;
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/visitors
     * HTTP method: GET
     *
     * @return List of all visitors
     */
    @RequestMapping(value = "/visitors", method = RequestMethod.GET)
    public ResponseEntity<?> getVisitors() {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        Iterable<VisitorEntity> visitorList = visitorService.findAll();
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{visitorList}, Locale.getDefault()));
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
    @RequestMapping(value = "/visitors/{visitorId}", method = RequestMethod.GET)
    public ResponseEntity<?> getVisitor(@PathVariable long visitorId) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitorId}, Locale.getDefault()));
        VisitorEntity visitor = visitorService.findOne(visitorId);
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{visitor}, Locale.getDefault()));
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
    @RequestMapping(value = "/visitors/add", method = RequestMethod.POST)
    public ResponseEntity<?> addVisitor(@RequestBody VisitorEntity visitor) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitor}, Locale.getDefault()));
        VisitorEntity newVisitor = visitorService.save(visitor);
        UsersEntity newUser = new UsersEntity(visitor.getUsername(), visitor.getPassword(), Arrays.asList(rolesService.getByName("VISITOR")));
        signupService.addUser(newUser);
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{newVisitor}, Locale.getDefault()));
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
    @RequestMapping(value = "/visitors/update/{visitorId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateVisitor(@PathVariable long visitorId,
                                           @RequestBody VisitorEntity visitor) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitor}, Locale.getDefault()));
        VisitorEntity updatedVisitor = visitorService.save(visitor);
        UsersEntity usersEntity = userService.findByUsername(updatedVisitor.getUsername());
        if (usersEntity == null) {
            UsersEntity newUser = new UsersEntity(visitor.getUsername(), visitor.getPassword(), Arrays.asList(rolesService.getByName("VISITOR")));
            signupService.addUser(newUser);
        } else {
            usersEntity.setPassword(updatedVisitor.getPassword());
            signupService.addUser(usersEntity);
        }
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{updatedVisitor}, Locale.getDefault()));
        return new ResponseEntity<>(updatedVisitor, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/visitor/visitors/delete/{visitorId}
     * HTTP method: POST
     *
     * @param visitorId - Id of visitor which need delete
     */
    @RequestMapping(value = "/visitors/delete/{visitorId}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteVisitor(@PathVariable long visitorId) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitorId}, Locale.getDefault()));
        VisitorEntity visitor = visitorService.findOne(visitorId);
        visitorService.delete(visitor);
        UsersEntity usersEntity = userService.findByUsername(visitor.getUsername());
        signupService.delUser(usersEntity);
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
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
    @RequestMapping(value = "/visitors/getByUsername", method = RequestMethod.POST)
    public ResponseEntity<?> getVisitorByUsername(@RequestBody String username) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{username}, Locale.getDefault()));
        VisitorEntity neededVisitor = visitorService.findByUsername(username);
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{neededVisitor}, Locale.getDefault()));
        return new ResponseEntity<>(neededVisitor, HttpStatus.OK);
    }

    @RequestMapping(value = "/visitors/addTour", method = RequestMethod.POST)
    public ResponseEntity<?> addTourToVisitor(@RequestBody TourVisitorDao tvd) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{tvd.getVisitorId()}, Locale.getDefault()));
        VisitorEntity visitorEntity = visitorService.findOne(tvd.getVisitorId());
        Set<TourEntity> set = visitorEntity.getTourEntitySet();
        set.add(tourService.findOne(tvd.getTourId()));
        VisitorEntity updatedVisitor = visitorService.save(visitorEntity);
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{updatedVisitor}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/toursCheck", method = RequestMethod.POST)
    public ResponseEntity<?> isTourInFavourites(@RequestBody TourVisitorDao tVd) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{tVd}, Locale.getDefault()));
        VisitorEntity visitor = visitorService.findOne(tVd.getVisitorId());
        Set<TourEntity> toursSet = visitor.getTourEntitySet();
        TourEntity tourEntity = tourService.findOne(tVd.getTourId());
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{toursSet}, Locale.getDefault()));
        return new ResponseEntity<>(toursSet.contains(tourEntity), HttpStatus.OK);
    }

    @RequestMapping(value = "/visitors/removeTour", method = RequestMethod.POST)
    public ResponseEntity<?> removeTourFromVisitor(@RequestBody TourVisitorDao tVd) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{tVd}, Locale.getDefault()));
        VisitorEntity visitorEntity = visitorService.findOne(tVd.getVisitorId());
        Set<TourEntity> visitorToursSet = visitorEntity.getTourEntitySet();
        visitorToursSet.remove(tourService.findOne(tVd.getTourId()));
        visitorEntity.setTourEntitySet(visitorToursSet);
        VisitorEntity updatedVisitor = visitorService.save(visitorEntity);
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{updatedVisitor}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/tours/findAll/{visitorId}", method = RequestMethod.GET)
    public ResponseEntity<?> getVisitorTours(@PathVariable long visitorId) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitorId}, Locale.getDefault()));
        VisitorEntity visitor = visitorService.findOne(visitorId);
        Set<TourEntity> toursSet = visitor.getTourEntitySet();
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{toursSet}, Locale.getDefault()));
        return new ResponseEntity<>(toursSet, HttpStatus.OK);
    }
}

