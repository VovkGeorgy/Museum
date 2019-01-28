package by.home.museum;

import by.home.museum.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Class for project running
 */
public class MuseumRunner {

    /**
     * Method create spring application context entity for project running
     *
     * @param args input arguments
     * @throws Exception some exception
     */
    public static void main(final String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
