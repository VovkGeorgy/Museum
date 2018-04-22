package by.home.museum.controller;

import by.home.museum.service.TourService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.mock;

@Configuration
public class TourControllerTestConfig {
    @Bean
    public MessageSource messageSource() {
        return mock(MessageSource.class);
    }

    @Bean
    public TourService tourService() {
        return mock(TourService.class);
    }

    @Bean
    public EntityManager entityManager() {
        return mock(EntityManager.class);
    }

    @Bean
    public TourController tourController() {
        return new TourController();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
