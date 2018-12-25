package by.home.museum.initialize;

import by.home.museum.config.AppConfig;
import by.home.museum.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * {@link org.springframework.web.WebApplicationInitializer WebApplicationInitializer}
 * to register a DispatcherServlet and use Java-based Spring configuration.
 * <p>
 * Implementations are required to implement:
 * {@link #getRootConfigClasses()} -- for "root" application context configuration.
 * {@link #getServletConfigClasses()} -- for application context (Spring MVC infrastructure) configuration.
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}