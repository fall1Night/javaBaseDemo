package com.example.learningdemo.Exception_8;

public class testThrow {
    // 嵌套捕获时如果外层也有符合捕获条件时也会捕获
        static void proc(){
            try{
                throw new NullPointerException("demo");
            }catch(NullPointerException e){
                System.out.println("Caught inside proc");
                throw e;
            }
        }

        public static void main(String [] args){
            try{
                proc();
            }catch(Exception e){
                System.out.println("Recaught: "+e);
            }
        }
    }

