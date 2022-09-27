package com.example.learningdemo.designModel_13.careateType.builder;

/**
 * 建造者父类
 */
public abstract class Builder {

    public abstract void setPart(String name, String type);

    public abstract Product getProduct();
}