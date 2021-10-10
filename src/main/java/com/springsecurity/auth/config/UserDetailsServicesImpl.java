package com.springsecurity.auth.config;

import com.springsecurity.auth.dao.User;
import com.springsecurity.auth.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServicesImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if(user == null)
            throw new UsernameNotFoundException("User not for the id/email >> "+email);


        System.out.println("roles >>>>   "+user.getRoles());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),user.getRoles());

    }
}
