package by.home.museum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@CrossOrigin
@RestController
public class MainController {

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public String defaultPage() {
        return "This is default page!";
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public String adminPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        return "This page is for ROLE_ADMIN only!";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {
        String message = "Login page";
        if (error != null) {
            message += " error Invalid username and password!";
        }
        if (logout != null) {
            message += " msg You've been logged out successfully.";
        }
        return message;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accesssDenied() {
        String message403 = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            message403 += "username" + userDetail.getUsername();
        }
        return message403;
    }

    /**
     * Set the locale configuration with a param 'locale'
     *
     * @param language - language, what we needed
     * @param location - location, where you are
     * @return message from locale settings
     */
    @RequestMapping(value = "/setLocale", method = RequestMethod.GET)
    public String setLocale(String language, String location, ModelMap model) {
        Locale defLocale = new Locale(language, location);
        Locale.setDefault(defLocale);
        return messageSource.getMessage("locale.changed", new Object[]{null}, Locale.getDefault());
    }

}
