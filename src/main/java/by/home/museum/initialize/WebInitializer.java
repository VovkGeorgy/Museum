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

    /**
     * Specify {@code @Configuration} and/or {@code @Component} classes for the
     * {@linkplain #createRootApplicationContext() root application context}.
     *
     * @return the configuration for the root application context, or {@code null}
     * if creation and registration of a root context is not desired
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * Specify {@code @Configuration} and/or {@code @Component} classes for the
     * {@linkplain #createServletApplicationContext() Servlet application context}.
     *
     * @return the configuration for the Servlet application context, or
     * {@code null} if all configuration is specified through root config classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    /**
     * Specify the servlet mapping(s) for the DispatcherServlet;
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}