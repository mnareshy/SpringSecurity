package com.springsecurity.methodlevelSecurity;

import com.springsecurity.auth.config.UserDetailsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enables Method level security  on Service level classes , repository interfaces
//@Order(1)
public class GlobalMethodWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServicesImpl userDetailsServices;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.formLogin();
         http.httpBasic();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        System.out.println("auth >>>> " +auth.isConfigured());

        auth.userDetailsService(userDetailsServices).passwordEncoder(passwordEncoder);

    }
}
