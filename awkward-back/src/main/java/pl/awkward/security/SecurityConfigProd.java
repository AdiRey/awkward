package pl.awkward.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.awkward.profiles.ProdProfile;

@ProdProfile
@Configuration
public class SecurityConfigProd extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable();

        http
                .authorizeRequests()
                    .anyRequest().authenticated()
                .and()
                    .formLogin().permitAll();
    }
}
