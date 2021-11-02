package com.springsecurity.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


/*@Configuration
@EnableResourceServer*/
public class OAuthResourceServerWithJWTURLAccessToPublicKey extends ResourceServerConfigurerAdapter {


    public static final String PET_STORE_RESOURCE_ID = "petDetails";

//    Setting the folling property in application.properties let ResourceServer understand that the  public key is supplied from the URL exposed
//    springsecurity.oauth2.resource.jwd.key-uri=http:\\localhost:\\8080/oauth/token_key
//    Hence you dont have crete TokenSrore and JWTAccessTokenConverter.


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        System.out.println("OAuthResourceServer >>>>"+PET_STORE_RESOURCE_ID);

        resources.resourceId(PET_STORE_RESOURCE_ID);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {

        System.out.println("OAuthResourceServer authorizeRequests>>>>");

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/petdetailsService/petDetails/{petID:^[0-1]*$}").
                hasAnyRole("ADMIN","USER")
                .mvcMatchers(HttpMethod.POST,"/petdetailsService/petDetails").
                hasRole("ADMIN").anyRequest().authenticated()
                .and().cors().and().csrf().disable();

        System.out.println(">>>>> "+ SecurityContextHolder.getContext().getAuthentication());
    }



}


