package com.example.learningdemo.Exception;

public class TestFinally {
   static  int  a=1;
    static void proc1(){

        try{
            System.out.println("inside proc1");
            throw new RuntimeException("demo");
        }finally{
            System.out.println("proc1's finally");
        }
    }
    static void proc2(){
        try{
          a=2;
            System.out.println("inside proc2");
            return ;
        } finally{
            a=3;
            System.out.println("proc2's finally");
        }
    }
    static void proc3(){
        try{
            System.out.println("inside proc3");
        }finally{
            System.out.println("proc3's finally");
        }
    }
    public static void main(String [] args){
        try{
            proc1();
        }catch(Exception e){
            System.out.println("Exception caught");
        }
        // 当try语句中存在return 时,finally语句块中的代码会执行且在return之前执行
        proc2();
        System.out.println("a:"+a);
        proc3();
    }
}
