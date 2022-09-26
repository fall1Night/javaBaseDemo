package com.example.learningdemo.designModel_13.careateType.factoryMethod;

public class SmsSender implements Sender {

    @Override
    public void Send() {
        System.out.println("this is sms sender!");
    }
}