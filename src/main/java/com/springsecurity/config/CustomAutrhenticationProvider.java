package com.springsecurity.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAutrhenticationProvider implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        String userName = authentication.getName();
        String passWord = authentication.getCredentials().toString();

        System.out.println("USer Details :"+ userName +" "+passWord);

        if(userName.equals("sai") && passWord.equals("ram")){

            return new UsernamePasswordAuthenticationToken(userName,passWord, Arrays.asList());
        }
        else
            throw new BadCredentialsException("User Or Password is wrong for the User : "+userName);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
