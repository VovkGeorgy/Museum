package by.home.museum.controller;

import by.home.museum.entity.TourEntity;
import by.home.museum.entity.TourVisitorEntity;
import by.home.museum.entity.UsersEntity;
import by.home.museum.entity.VisitorEntity;
import by.home.museum.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private TourService tourService;

    @Autowired
    private UserService userService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private SignupService signupService;

    @Autowired
    private MessageSource messageSource;

    private static final Logger LOGGER = LoggerFactory.getLogger(VisitorController.class);


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
        UsersEntity newUser = new UsersEntity(visitor.getUsername(), visitor.getPassword());
        newUser.setRoles(Arrays.asList(rolesService.getByName("USER")));
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
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/visitors/update/{visitorId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateVisitor(@PathVariable long visitorId,
                                           @RequestBody VisitorEntity visitor) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitor}, Locale.getDefault()));
        VisitorEntity updatedVisitor = visitorService.save(visitor);
        UsersEntity usersEntity = userService.findByUsername(updatedVisitor.getUsername());
        if (usersEntity == null) {
            UsersEntity newUser = new UsersEntity(visitor.getUsername(), visitor.getPassword());
            newUser.setRoles(Arrays.asList(rolesService.getByName("USER")));
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
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    public ResponseEntity<?> addTourToVisitor(@RequestBody TourVisitorEntity tourVisitorEntity) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourVisitorEntity}, Locale.getDefault()));
        TourEntity tourEntity = tourService.findOne(tourVisitorEntity.getTourId());
        VisitorEntity visitorEntity = visitorService.findOne(tourVisitorEntity.getVisitorId());
        TourVisitorEntity newTourVisitor = new TourVisitorEntity(tourVisitorEntity.getTourId(), tourVisitorEntity.getVisitorId(), tourEntity, visitorEntity);
        visitorService.addTourToVisitor(newTourVisitor);
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{newTourVisitor}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/visitors/removeTour", method = RequestMethod.POST)
    public ResponseEntity<?> removeTourFromVisitor(@RequestBody TourVisitorEntity tourVisitorEntity) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourVisitorEntity}, Locale.getDefault()));
        TourEntity tEntity = tourService.findOne(tourVisitorEntity.getTourId());
        VisitorEntity vEntity = visitorService.findOne(tourVisitorEntity.getVisitorId());
        TourVisitorEntity newTourVisitor = new TourVisitorEntity(tourVisitorEntity.getTourId(), tourVisitorEntity.getVisitorId(), tEntity, vEntity);
        visitorService.removeTourFromVisitor(newTourVisitor);
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{newTourVisitor}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/toursCheck", method = RequestMethod.POST)
    public ResponseEntity<?> checkTourExhibits(@RequestBody TourVisitorEntity tourVisitorEntity) {
        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{tourVisitorEntity}, Locale.getDefault()));
        VisitorEntity visitor = visitorService.findOne(tourVisitorEntity.getVisitorId());
        Collection<TourVisitorEntity> toursList = visitor.getTourVisitorsByVisitorId();
        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{toursList}, Locale.getDefault()));
        for (TourVisitorEntity tempObj : toursList) {
            if (Objects.equals(tempObj.getTourId(), tourVisitorEntity.getTourId())) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/tours/{visitorId}", method = RequestMethod.GET)
//    public ResponseEntity<?> checkTourExhibits(@PathVariable long visitorId) {
//        LOGGER.debug(messageSource.getMessage("controller.getRequest", new Object[]{visitorId}, Locale.getDefault()));
//        VisitorEntity visitor = visitorService.findOne(visitorId);
//        Collection<TourVisitorEntity> toursList = visitor.getTourVisitorsByVisitorId();
//        LOGGER.debug(messageSource.getMessage("controller.returnResponse", new Object[]{toursList}, Locale.getDefault()));
//        return new ResponseEntity<>(toursList, HttpStatus.OK);
//    }
}

