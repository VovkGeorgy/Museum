package by.home.museum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataConfig.class, WebConfig.class, SecurityConfig.class})
public class AppConfig {
}