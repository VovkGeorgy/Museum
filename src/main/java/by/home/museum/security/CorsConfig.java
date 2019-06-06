package by.home.museum.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Order(-5)
public class CorsConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .requestMatchers()
                .antMatchers(HttpMethod.OPTIONS, "/oauth/token")
                .and()
                .csrf().disable()
                .addFilterBefore(new SimpleCorsFilter(), BasicAuthenticationFilter.class);
    }
}
