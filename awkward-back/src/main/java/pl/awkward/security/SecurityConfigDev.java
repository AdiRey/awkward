package pl.awkward.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.awkward.profiles.DevProfile;

@DevProfile
@Configuration
public class SecurityConfigDev extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable();
        http
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}