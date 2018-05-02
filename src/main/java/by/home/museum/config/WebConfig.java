package by.home.museum.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan("by.home.museum")
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * Add to converters a json message converter
     *
     * @param converters - converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(converter);
    }

    /**
     * Been of messageSource, to success logging
     *
     * @return messageSource
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Been of default locale, to success logging
     *
     * @return defaultLocale
     */
    @Bean
    public Locale locale() {
        Locale defaultLocale = new Locale("en", "US");
        Locale.setDefault(defaultLocale);
        return defaultLocale;
    }


    /**
     * Add resource handler in registry of resource handlers for serving static resources such as images,
     * css files and others.
     *
     * @param registry - registry of resource handlers
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*").addResourceLocations("");
    }

    /**
     * Map a view controller to the given URL path (or pattern) in order to render
     * a response with a pre-configured status code and view.
     *
     * @param registry - registry of view controllers
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/tours").setViewName("forward:/index.html");
        registry.addViewController("/tours/*").setViewName("forward:/index.html");
        registry.addViewController("/guides").setViewName("forward:/index.html");
        registry.addViewController("/guides/*").setViewName("forward:/index.html");
        registry.addViewController("/exhibits/*").setViewName("forward:/index.html");
        registry.addViewController("/visitors").setViewName("forward:/index.html");
        registry.addViewController("/about").setViewName("forward:/index.html");
        registry.addViewController("/login").setViewName("forward:/index.html");
        registry.addViewController("/404").setViewName("forward:/index.html");
        registry.addViewController("/403").setViewName("forward:/index.html");
    }

    /**
     * Been of ObjectMapper wich provides functionality for reading and writing JSON
     *
     * @return objectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

}
