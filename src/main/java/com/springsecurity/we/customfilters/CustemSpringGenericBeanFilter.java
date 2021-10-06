package com.springsecurity.we.customfilters;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class CustemSpringGenericBeanFilter extends GenericFilterBean {

    //init params from WEB.xml will be loaded by Spring
    private String name;
    private int age;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("CustemSpringGenericBeanFilter >>  ");
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
