package pl.awkward.security;

import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.awkward.configuration.profiles.DevProfile;
import pl.awkward.security.filter.JwtCreateFilter;
import pl.awkward.security.filter.JwtVerifyUserFilter;
import pl.awkward.security.jwt.JwtManageComponent;

@DevProfile
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigDev extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
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
/*        http
                .csrf().disable()
                .cors().and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtCreateFilter())
                .addFilterAfter(jwtVerifyUserFilter(), JwtCreateFilter.class)
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                    .antMatchers(HttpMethod.GET, "/api/users/8").permitAll()
                    .anyRequest().authenticated();*/
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

//        configuration.setAllowedOrigins(ImmutableList.of("http://localhost:4200"));
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
