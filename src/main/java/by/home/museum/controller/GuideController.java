package by.home.museum.controller;

import by.home.museum.entity.GuideEntity;
import by.home.museum.entity.UsersEntity;
import by.home.museum.service.GuideService;
import by.home.museum.service.RolesService;
import by.home.museum.service.SignupService;
import by.home.museum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Locale;

@RestController
@RequestMapping("/guide")
public class GuideController {

    @Autowired
    private GuideService guideService;

    @Autowired
    private UserService userService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private SignupService signupService;

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(GuideController.class);

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guide/guides
     * HTTP method: GET
     *
     * @return List of all guides
     */
    @RequestMapping(value = "/guides", method = RequestMethod.GET)
    public ResponseEntity<?> getGuides() {
        logger.info(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        Iterable<GuideEntity> guideList = guideService.findAll();
        logger.info(messageSource.getMessage("controller.returnResponse", new Object[]{guideList}, Locale.getDefault()));
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
    @RequestMapping(value = "/guides/{guideId}", method = RequestMethod.GET)
    public ResponseEntity<?> getGuide(@PathVariable long guideId) {
        logger.info(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        GuideEntity guide = guideService.findOne(guideId);
        logger.info(messageSource.getMessage("controller.returnResponse", new Object[]{guide}, Locale.getDefault()));
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
    @RequestMapping(value = "/guides/add", method = RequestMethod.POST)
    public ResponseEntity<?> addGuide(@RequestBody GuideEntity guide) {
        logger.info(messageSource.getMessage("controller.getRequest", new Object[]{guide}, Locale.getDefault()));
        GuideEntity newGuide = guideService.save(guide);
        UsersEntity newAdmin = new UsersEntity(guide.getUsername(), guide.getPassword());
        newAdmin.setRoles(Arrays.asList(rolesService.getByName("ADMIN"), rolesService.getByName("USER")));
        signupService.addUser(newAdmin);
        logger.info(messageSource.getMessage("controller.returnResponse", new Object[]{newGuide}, Locale.getDefault()));
        return new ResponseEntity<>(newGuide, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port//guides/update/guideId
     * HTTP method: POST
     * Method update guide as a entity, and as ADMIN
     *
     * @param guide   - entity to update
     * @param guideId - id of needed guide
     * @return updated guide
     */
    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/guides/update/{guideId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateGuide(@PathVariable long guideId,
                                         @RequestBody GuideEntity guide) {
        logger.info(messageSource.getMessage("controller.getRequest", new Object[]{guide}, Locale.getDefault()));
        GuideEntity updatedGuide = guideService.save(guide);
        UsersEntity usersEntity = userService.findByUsername(updatedGuide.getUsername());
        if (usersEntity == null) {
            UsersEntity newUser = new UsersEntity(guide.getUsername(), guide.getPassword());
            newUser.setRoles(Arrays.asList(rolesService.getByName("ADMIN"), rolesService.getByName("USER")));
            signupService.addUser(newUser);
        } else {
            usersEntity.setPassword(updatedGuide.getPassword());
            signupService.addUser(usersEntity);
        }
        logger.info(messageSource.getMessage("controller.returnResponse", new Object[]{updatedGuide}, Locale.getDefault()));
        return new ResponseEntity<>(updatedGuide, HttpStatus.OK);
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/guide/guides/delete/{guideId}
     * HTTP method: POST
     * Method delete guide as a entity, and as ADMIN
     *
     * @param guideId - Id of guide which need delete
     */
    @RequestMapping(value = "/guides/delete/{guideId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteGuide(@PathVariable long guideId) {
        logger.info(messageSource.getMessage("controller.getRequest", new Object[]{guideId}, Locale.getDefault()));
        GuideEntity guide = guideService.findOne(guideId);
        guideService.delete(guide);
        UsersEntity usersEntity = userService.findByUsername(guide.getUsername());
        signupService.delUser(usersEntity);
        logger.info(messageSource.getMessage("controller.returnResponse", new Object[]{null}, Locale.getDefault()));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}