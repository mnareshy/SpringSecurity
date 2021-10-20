package com.springsecurity.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

/*
@Configuration
@Order(1)
*/
public class PetStoreSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServicesImpl userDetailsServices;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

     /* @Override
      public void configure(WebSecurity web) throws Exception {

          web.debug(true);
              super.configure(web);
      }*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        System.out.println("auth >>>> " +auth.isConfigured());

        auth.userDetailsService(userDetailsServices).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic();
        http.formLogin();
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/petdetailsService/petDetails/{petID:^[0-1]*$}").
                hasAnyRole("ADMIN","USER")
                .mvcMatchers(HttpMethod.POST,"/petdetailsService/petDetails").
                hasRole("ADMIN").anyRequest().authenticated()
                .and().cors().and().csrf().disable();

        System.out.println(">>>>> "+SecurityContextHolder.getContext().getAuthentication());

/*

        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println("http Authorization >>>> "+ authorities.toArray());

*/

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

}
