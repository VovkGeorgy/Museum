package by.home.museum.controller;

import by.home.museum.service.GuideService;
import by.home.museum.service.RolesService;
import by.home.museum.service.SignupService;
import by.home.museum.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.mock;

@Configuration
public class GuideControllerTestConfig {
    @Bean
    public MessageSource messageSource() {
        return mock(MessageSource.class);
    }

    @Bean
    public GuideService guideService() {
        return mock(GuideService.class);
    }

    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    public RolesService rolesService() {
        return mock(RolesService.class);
    }

    @Bean
    public SignupService signupService() {
        return mock(SignupService.class);
    }

    @Bean
    public EntityManager entityManager() {
        return mock(EntityManager.class);
    }

    @Bean
    public GuideController guideController() {
        return new GuideController();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
