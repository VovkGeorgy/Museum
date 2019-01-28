package by.home.museum.controller;

import lombok.extern.slf4j.Slf4j;
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

import java.util.Locale;

/**
 * About component rest controller
 */
@Slf4j
@RestController
@RequestMapping("/abo")
public class AboutController {

    private final MessageSource messageSource;

    @Autowired
    public AboutController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * this method maps the following URL & http method
     * URL: http://hostname:port/abo/whoiam
     * HTTP method: GET
     *
     * @return authentication of current user
     */
    @RequestMapping(value = "/whoiam", method = RequestMethod.GET)
    public ResponseEntity<?> getRole() {
        log.debug(messageSource.getMessage("controller.getRequest", new Object[]{null}, Locale.getDefault()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug(messageSource.getMessage("controller.returnResponse", new Object[]{authentication}, Locale.getDefault()));
        return new ResponseEntity<>(authentication.getAuthorities(), HttpStatus.OK);
    }

    @RequestMapping(value = "/adminOnly", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminInfo() {
        String message = "This is admin secured info";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
