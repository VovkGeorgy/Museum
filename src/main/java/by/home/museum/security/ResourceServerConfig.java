package by.home.museum.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous().disable()
                .requestMatchers()
                .antMatchers("/abo/**")
                .antMatchers("/guide/**")
                .antMatchers("/visitor/**")
                .and()
                .authorizeRequests()
                .antMatchers("/visitor/**").access("hasRole('ADMIN') or hasRole('GUIDE')")
                .antMatchers("/guide/**").access("hasRole('ADMIN')")
                .antMatchers("/abo/**").access("hasRole('ADMIN')")
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}