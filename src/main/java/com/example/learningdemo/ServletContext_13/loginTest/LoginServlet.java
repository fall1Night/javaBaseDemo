package com.example.learningdemo.ServletContext_13.loginTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /*
        request：包含请求信息

        response：响应数据给浏览器

    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //1.获取数据
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username=" + name + "..." + "password=" + password);
        //2.校验数据
        //向客户端输出内容
        PrintWriter pw = response.getWriter();
        if ("admin".equals(name) && "123".equals(password)) {
            System.out.println("登陆成功");
            pw.write("login success...");

            //成功的次数累加
            Object obj = getServletContext().getAttribute("count");//获取以前存取的count值

            Integer count = 0;
            if (obj != null) {
                count = (Integer) obj;
            }
            System.out.println("已登陆成功的次数是：" + count);
            getServletContext().setAttribute("count", count + 1);

            response.setStatus(302);    //设置状态码，重新定位目标位置
            //定位跳转的位置是哪一个页面
            response.setHeader("Location", "login_success.html");    //成功就跳转到login_success.html
        } else {
            System.out.println("登陆失败");
            pw.write("login failed...");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
