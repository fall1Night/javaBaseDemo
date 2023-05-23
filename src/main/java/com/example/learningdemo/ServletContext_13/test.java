package com.example.learningdemo.ServletContext_13;

public class test {
    public static void main(String[] args) {
        String a = "中国铁路北京局,集团有限公司";
        System.out.println(a.substring(a.indexOf("路") + 1, a.indexOf("集")));
        String s = a.split(",")[0];
        System.out.println();


    }
}
