package com.springsecurity.oauth2;


import com.springsecurity.auth.config.UserDetailsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.security.PrivateKey;

@Configuration
@EnableAuthorizationServer
public class OAuthorizationServer extends AuthorizationServerConfigurerAdapter {

    public static final String PET_STORE_RESOURCE_ID = "petDetails";
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServicesImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(new InMemoryTokenStore()).authenticationManager(authenticationManager).
                userDetailsService(userDetailsService);



    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory().withClient("petstoreclientapp").secret(passwordEncoder.encode("secret")).authorizedGrantTypes("password","refresh_token")
                .scopes("read","write").resourceIds(PET_STORE_RESOURCE_ID);
    }

}
