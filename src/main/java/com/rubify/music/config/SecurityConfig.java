package com.rubify.music.config;

import com.rubify.music.service.UserDetailsServiceImpl;
import com.rubify.music.utils.CustomPasswordEncoder;
import com.rubify.music.utils.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;
    @Autowired
    private JwtFilter jwtFilter;

    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder.getPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http = http.csrf().disable().cors().disable();
      http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
      http = http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
      }).and();
      http.authorizeRequests()
              .antMatchers("/api/auth/**").permitAll() //Remove token auth from these exposed api endpoins
              .antMatchers("/api/v1/**").permitAll()
              .anyRequest().authenticated();
      http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
