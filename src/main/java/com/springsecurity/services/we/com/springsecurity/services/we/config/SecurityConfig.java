package com.springsecurity.services.we.com.springsecurity.services.we.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder ;

    @Autowired
    CustomAutrhenticationProvider customAutrhenticationProvider;

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();


        UserDetails user =  User.withUsername("sai").password(passwordEncoder.encode("mnr")).authorities("read").build();
        inMemoryUserDetailsManager.createUser(user);



        auth.userDetailsService(inMemoryUserDetailsManager).passwordEncoder(passwordEncoder);
    }
*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAutrhenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
//        http.formLogin()
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

//      Endpoints otherthan  "/name " still works
//      http.authorizeRequests().antMatchers("/name").authenticated().and().httpBasic().and().formLogin();

//      Endpoints otherthan  "/name "  will be denied
        http.authorizeRequests().antMatchers("/name").authenticated().anyRequest().denyAll().and().httpBasic().and().formLogin();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
