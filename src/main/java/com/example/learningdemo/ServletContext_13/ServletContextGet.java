package com.example.learningdemo.ServletContext_13;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class ServletContextGet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doGet(String name) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String text = (String) context.getAttribute(name);
        System.out.println("取参数++++++++++++++++++" + text);
    }

    protected void doPost(String name) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(name);
    }

}
