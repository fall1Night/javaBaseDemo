package com.example.learningdemo.pojo;

import java.util.Objects;

public class Person {
    public String name;
    public Integer age;
    public String sex;
    public Integer salary;

    public Person(){

    }
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, Integer age, String sex, Integer salary) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(age, person.age) &&
                Objects.equals(sex, person.sex) &&
                Objects.equals(salary, person.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex, salary);
    }
}
