package com.springsecurity.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;


/*@Configuration
@EnableResourceServer*/
public class OAuthResourceServerWithJWT extends ResourceServerConfigurerAdapter {

    public static final String PET_STORE_RESOURCE_ID = "petDetails";
    @Value("${publickey}")
    String publickey;

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

    @Bean
    public TokenStore tokenStoreForResourceServer()
    {
        return new JwtTokenStore(jwtAccessTokenConverterForResourceServer());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverterForResourceServer(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();

        jwtAccessTokenConverter.setVerifierKey(publickey);

        return jwtAccessTokenConverter;
    }

}
