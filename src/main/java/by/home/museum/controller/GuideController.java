package by.home.museum.controller;

import by.home.museum.dto.TourGuideDto;
import by.home.museum.entity.GuideEntity;
import by.home.museum.entity.UsersEntity;
import by.home.museum.service.GuideService;
import by.home.museum.service.RolesService;
import by.home.museum.service.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Locale;

/**
 * Guide component rest controller
 */
@Slf4j
@RestController
@RequestMapping("/api/guide")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuideController {

    private final GuideService guideService;
    private final RolesService rolesService;
    private final SignupService signupService;
    private final MessageSource messageSource;

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guide/guides
     * HTTP method: GET
     *
     * @return List of all guides
     */
    @RequestMapping(value = "/guides", method = RequestMethod.GET)
    public ResponseEntity<?> getGuides() {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        Iterable<GuideEntity> guideList = guideService.findAll();
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{guideList}, Locale.getDefault()));
        return new ResponseEntity<>(guideList, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guide/guides/{guideId}
     * HTTP method: GET
     *
     * @param guideId - id of needed guide
     * @return guide by Id
     */
    @RequestMapping(value = "/{guideId}", method = RequestMethod.GET)
    public ResponseEntity<?> getGuide(@PathVariable long guideId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        GuideEntity guide = guideService.findOne(guideId);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{guide}, Locale.getDefault()));
        return new ResponseEntity<>(guide, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guide/guides/add
     * HTTP method: POST
     * Method add guide as a entity, and as ADMIN
     *
     * @param guide - entity to save
     * @return saved guide
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addGuide(@RequestBody GuideEntity guide) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{guide}, Locale.getDefault()));
        GuideEntity newGuide = guideService.save(guide);
        UsersEntity newAdmin = new UsersEntity(guide.getUsername(), guide.getPassword());
        newAdmin.setRoles(Arrays.asList(rolesService.getByName("ADMIN"), rolesService.getByName("USER")));
        signupService.addUser(newAdmin);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{newGuide}, Locale.getDefault()));
        return new ResponseEntity<>(newGuide, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port//guides/update/guideId
     * HTTP method: POST
     * Method update updatedGuide as a entity, and as ADMIN
     *
     * @param updatedGuide - entity to update
     * @return updated updatedGuide
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateGuide(@RequestBody GuideEntity updatedGuide) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{updatedGuide}, Locale.getDefault()));
        GuideEntity persistGuide = guideService.updateGuide(updatedGuide);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{updatedGuide}, Locale.getDefault()));
        return new ResponseEntity<>(persistGuide, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guide/guides/delete/{guideId}
     * HTTP method: POST
     * Method delete guide as a entity, and as ADMIN
     *
     * @param guideId - Id of guide which need delete
     */
    @RequestMapping(value = "/delete/{guideId}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteGuide(@PathVariable long guideId) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{guideId}, Locale.getDefault()));
        guideService.delete(guideService.findOne(guideId));
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guide/guides/getByUsername
     * HTTP method: POST
     * Method return guide entity by it usename
     *
     * @param username - username of guide which need
     */
    @RequestMapping(value = "/getByUsername", method = RequestMethod.POST)
    public ResponseEntity<?> getByUsername(@RequestBody String username) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{username}, Locale.getDefault()));
        GuideEntity guide = guideService.findByUsername(username);
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(guide, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guide/guides/removeTour
     * HTTP method: POST
     *
     * @param tgd tours-guide-dao entity
     * @return HTTP status OK
     */
    @RequestMapping(value = "/removeTours", method = RequestMethod.POST)
    public ResponseEntity<?> removeTourFromGuide(@RequestBody TourGuideDto tgd) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tgd}, Locale.getDefault()));
        GuideEntity updatedGuide = guideService.removeTours(tgd);
        return new ResponseEntity<>(updatedGuide, HttpStatus.OK);
    }

    /**
     * Add tour to guide
     *
     * @param tgd tour-guide dto
     * @return updated guide
     */
    @RequestMapping(value = "/addTours", method = RequestMethod.POST)
    public ResponseEntity<?> addTourToGuide(@RequestBody TourGuideDto tgd) {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{tgd}, Locale.getDefault()));
        GuideEntity updatedGuide = guideService.addTours(tgd);
        return new ResponseEntity<>(updatedGuide, HttpStatus.OK);
    }
}
