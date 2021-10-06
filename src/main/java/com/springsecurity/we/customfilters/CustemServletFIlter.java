package com.springsecurity.we.customfilters;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

//@Component
public class CustemServletFIlter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("CustemServletFIlter >> Before Invoking ");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("CustemServletFIlter >> After Invoking ");

    }
}
