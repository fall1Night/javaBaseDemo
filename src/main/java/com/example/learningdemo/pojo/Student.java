package com.example.learningdemo.pojo;

public class Student extends Person {
    public String major;

    public Student() {

    }

    public Student(String name, Integer age, String major) {
        super(name, age);
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                '}';
    }

    public String getMajor(String major) {
        return "This student major" + major;
    }

    public Integer getNumX(Integer a,Integer b){
        return a+b;
    }
}
