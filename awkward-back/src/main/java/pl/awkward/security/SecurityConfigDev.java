package pl.awkward.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.awkward.configuration.profiles.DevProfile;
import pl.awkward.jwtKey.KeyPairContainer;
import pl.awkward.security.jwt.JwtCreateFilter;

@DevProfile
@Configuration
@RequiredArgsConstructor
public class SecurityConfigDev extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;
    private final KeyPairContainer keyPairContainer;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    public JwtCreateFilter jwtCreateFilter() throws Exception{
        JwtCreateFilter jwtCreateFilter =  new JwtCreateFilter(keyPairContainer);
        jwtCreateFilter.setAuthenticationManager(authenticationManager());
        return jwtCreateFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable();
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtCreateFilter())
                .authorizeRequests()
                    .anyRequest().permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
