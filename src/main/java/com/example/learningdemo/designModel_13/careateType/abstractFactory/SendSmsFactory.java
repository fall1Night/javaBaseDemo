package com.example.learningdemo.designModel_13.careateType.abstractFactory;

public class SendSmsFactory implements Provider {

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}