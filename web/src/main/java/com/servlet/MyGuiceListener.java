package com.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class MyGuiceListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets() {
                serve("/ajax/user").with(MyUserServlet.class);
                serve("/ajax/role").with(MyRoleServlet.class);
                serve("/ajax/accounting").with(MyAccountingServlet.class);
            }
        });
    }
}
