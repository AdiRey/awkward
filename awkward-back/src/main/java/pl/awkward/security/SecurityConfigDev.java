package pl.awkward.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.awkward.configuration.profiles.DevProfile;
import pl.awkward.security.jwt.JwtCreateFilter;
import pl.awkward.security.jwt.JwtManageComponent;
import pl.awkward.security.jwt.JwtVerifyUserFilter;

@DevProfile
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigDev extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;
    private final JwtManageComponent jwtManageComponent;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    public JwtCreateFilter jwtCreateFilter() throws Exception{
        JwtCreateFilter jwtCreateFilter =  new JwtCreateFilter(this.jwtManageComponent);
        jwtCreateFilter.setAuthenticationManager(authenticationManager());
        return jwtCreateFilter;
    }

    public JwtVerifyUserFilter jwtVerifyUserFilter() {
        return new JwtVerifyUserFilter(this.jwtManageComponent);
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
                .addFilterAfter(jwtVerifyUserFilter(), JwtCreateFilter.class)
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/users/8").permitAll()
                    .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
