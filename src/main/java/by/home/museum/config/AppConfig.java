package by.home.museum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring context configuration class
 */
@Configuration
@Import({DataConfig.class, WebConfig.class})
public class AppConfig {
}
