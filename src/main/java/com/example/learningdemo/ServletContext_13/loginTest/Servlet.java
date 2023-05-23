package com.example.learningdemo.ServletContext_13.loginTest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //1.获取全局配置参数
        ServletContext context = getServletContext();    //创建对象
        String rrrr = context.getInitParameter("rrrr");
        System.out.println("rrrr" + "..." + rrrr);

        //2.获取web应用中的资源
        /*
         * 这里如果想获取web工程下的资源,用普通的FileInputStream写法是不对的
         * 因为路径不正确,这里的相对路径是根据jre来确定的,但是这里是一个web工程
         * jre后面由tomcat管理,所以这里真正的相对路径是tomcat里的bin目录
         */
		/*
		Properties pro=new Properties();	//创建属性对象
		InputStream is=new FileInputStream("src/config.properties");
		pro.load(is);
		String name=pro.getProperty("address");
		System.out.println(name);
		*/
        //应该使用ServletContext
        //1)第一种方式，使用getRealPath()方法,先获取路径再获取流对象
        ServletContext sc2 = getServletContext();    //获取ServletContext对象
        String path = sc2.getRealPath("file/config.properties");        //获取给定的文件在服务器上面的绝对路径

        Properties pro = new Properties();        //创建属性对象
        InputStream is = new FileInputStream("path");
        pro.load(is);

        String name = pro.getProperty("address");    //获取address属性的值
        System.out.println(name);
        is.close();

        //2)第二种方式,使用getResourceAsStream(),根据相对路径直接获取流对象
        ServletContext sc3 = getServletContext();
        Properties pro2 = new Properties();
        //获取web工程下的资源转换成流对象,前面隐藏当前工程的根目录
        InputStream is2 = sc3.getResourceAsStream("file/config.properties");
        pro2.load(is2);
        String name2 = pro2.getProperty("address");
        System.out.println(name2);
        is2.close();

        //3).通过classloader(类加载器JDBC)去获取web工程下的资源
        Properties pro3 = new Properties();
        //获取该java文件的class,然后获取到加载这个class到虚拟机中的那个类加载器对象

        //默认的classloader的路径是下面这个路径,我们必须得回到JavaWeb_1这个目录下,才能进入file目录
        //E:\tomcat\apache-tomcat-7.0.57\wtpwebapps\JavaWeb_1\WEB-INF\classes
        InputStream is3 = this.getClass().getClassLoader().getResourceAsStream("../../file/config.properties");
        pro3.load(is3);
        String name3 = pro3.getProperty("address");
        System.out.println(name3);
        is3.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("bb");
    }
}
