package by.home.museum.controller;

import by.home.museum.entity.RolesEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Locale;

@RestController
@RequestMapping("/abo")
public class AboutController {

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(AboutController.class);

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/abo/whoiam
     * HTTP method: GET
     * @return authentication of current user
     */
    @RequestMapping(value = "/whoiam", method = RequestMethod.GET)
    public ResponseEntity<?> getRole() {
        logger.info(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info(messageSource.getMessage("controller.returnResponse", new Object[]{authentication}, Locale.getDefault()));
        return new ResponseEntity<>(authentication.getAuthorities(), HttpStatus.OK);
    }
}
