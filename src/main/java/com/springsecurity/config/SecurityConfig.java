package com.springsecurity.config;


import com.springsecurity.we.customfilters.CustemServletFIlter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder ;

    @Autowired
    CustomAutrhenticationProvider customAutrhenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        UserDetails user =  User.withUsername("sai").password(passwordEncoder.encode("mnr")).authorities("read").build();
        inMemoryUserDetailsManager.createUser(user);


        if(passwordEncoder instanceof DelegatingPasswordEncoder)
            System.out.println("*******DelegatingPasswordEncoder********");



        auth.userDetailsService(inMemoryUserDetailsManager).passwordEncoder(passwordEncoder);
    }

  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAutrhenticationProvider);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
//        http.formLogin()
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

//      Endpoints otherthan  "/name " still works
//      http.authorizeRequests().antMatchers("/name").authenticated().and().httpBasic().and().formLogin();

//      Endpoints otherthan  "/name "  will be denied
//      http.authorizeRequests().antMatchers("/name").authenticated().anyRequest().denyAll().and().httpBasic().and().formLogin();

        http.authorizeRequests().antMatchers("/name").authenticated().and().httpBasic().and().formLogin();
        http.cors().and().csrf().disable();
        http.addFilterBefore(new CustemServletFIlter(), BasicAuthenticationFilter.class);


    }

  /*  @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }*/


    @Bean
    public DelegatingPasswordEncoder passwordEncoder12(){

        Map<String,PasswordEncoder> encoder = new HashMap();

        encoder.put("BCrypt",new BCryptPasswordEncoder());
        encoder.put("SCrypt",new SCryptPasswordEncoder());

        return new DelegatingPasswordEncoder("BCrypt",encoder);

    }

    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/


}
