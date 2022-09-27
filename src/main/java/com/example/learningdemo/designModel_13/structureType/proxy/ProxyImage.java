package com.example.learningdemo.designModel_13.structureType.proxy;

/**
 * 3.对应代理类：ProxyImage。
 */
public class ProxyImage implements Image {

    private String fileName;
    private RealImage realImage;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
            System.out.println("图像从磁盘加载");
        }
        realImage.display();
    }
}
