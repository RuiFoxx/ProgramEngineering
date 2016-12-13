package com.servlet;

import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.util.logging.Logger;

@Singleton
public class MyAccountingServlet extends HttpServlet {
    @InjectLogger Logger logger = Logger.getLogger(MyAccountingServlet.class.getCanonicalName());
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, java.io.IOException {
        res.getWriter().println("MyAccountingServlet responds");
        logger.info("MyAccountingServlet responds!");
    }
}
