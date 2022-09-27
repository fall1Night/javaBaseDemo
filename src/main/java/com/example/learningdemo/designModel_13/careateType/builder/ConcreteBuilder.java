package com.example.learningdemo.designModel_13.careateType.builder;

/**
 * 建造者子类(具体实现)
 */
public class ConcreteBuilder extends Builder {

    private Product product = new Product();

    //存入产品
    @Override
    public void setPart(String name, String type) {
        product.setName(name);
        product.setType(type);
    }

    //取出产品
    @Override
    public Product getProduct() {
        return product;
    }
}