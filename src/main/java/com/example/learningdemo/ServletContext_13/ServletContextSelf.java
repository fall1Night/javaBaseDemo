package com.example.learningdemo.ServletContext_13;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletContextSelf extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ServletContext sc;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        // 第一种方式
        // sc = config.getServletContext();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 第二种方式
        // sc = this.getServletContext();

        // 第三种方式
        sc = request.getSession().getServletContext();
        // org.apache.catalina.core.ApplicationContextFacade@5bbca7c0
        System.out.println(sc);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
