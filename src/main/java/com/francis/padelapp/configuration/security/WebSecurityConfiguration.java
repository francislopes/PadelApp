package com.francis.padelapp.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailConfiguration userDetailConfiguration;

    @Override
    //Authentication configuration
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailConfiguration).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    //Authorization configuration
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/game/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST, "/game/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PATCH, "/game/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.PUT, "/game/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE, "/game/**").hasRole("ADMIN")
                .anyRequest().authenticated()
//                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }

}