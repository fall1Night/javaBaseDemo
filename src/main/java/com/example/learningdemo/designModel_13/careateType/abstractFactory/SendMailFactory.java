package com.example.learningdemo.designModel_13.careateType.abstractFactory;

public class SendMailFactory implements Provider {

    @Override
    public Sender produce() {
        return new MailSender();
    }
}