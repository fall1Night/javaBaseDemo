package com.example.learningdemo.designModel_13.careateType.abstractFactory;

public class MailSender implements Sender {
    @Override
    public void Send() {
        System.out.println("this is mailsender!");
    }
}