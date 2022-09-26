package com.example.learningdemo.designModel_13.careateType.prototype;

public class prototypeTest {
    public static void main(String[] args) {
        // 使用 ShapeCache 类来获取存储在 Hashtable 中的形状的克隆。
        ShapeCache.loadCache();
        Shape shapeCache1 = ShapeCache.getShape("1");
        Shape shapeCache2 = ShapeCache.getShape("2");
        Shape shapeCache3 = ShapeCache.getShape("3");
    }
}
