package com.example.learningdemo.testDemo;

import org.springframework.beans.BeanUtils;

public class test {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setName("1");
        a.setAge("0");
        b.setGender("nan");
        BeanUtils.copyProperties(a, b);
        System.out.println(b);


    }

    public static class A {
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    public static class B extends A {
        private String gender;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }

}
