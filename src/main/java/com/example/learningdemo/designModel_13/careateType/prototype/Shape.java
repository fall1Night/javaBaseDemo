package com.example.learningdemo.designModel_13.careateType.prototype;

/**
 * 实现Cloneable,重写clone方法,不实现时clone则会报CloneNotSupportedException错误
 */
public abstract class Shape implements Cloneable {

    private String id;
    protected String type;

    public abstract void draw();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object clone() {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        return object;
    }
}