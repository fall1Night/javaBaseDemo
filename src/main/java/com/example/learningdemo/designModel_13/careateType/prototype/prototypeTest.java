package com.example.learningdemo.designModel_13.careateType.prototype;

/**
 * @Author sxc
 * @Description 原型模式
 * @Date 2022/09/27
 */
public class prototypeTest {
    /**
     * 这种模式是实现了一个原型接口，该接口用于创建当前对象的克隆。当直接创建对象的代价比较大时，则采用这种模式。
     * 例如，一个对象需要在一个高代价的数据库操作之后被创建。我们可以缓存该对象，
     * 在下一个请求时返回它的克隆，在需要的时候更新数据库，以此来减少数据库调用。
     */
    public static void main(String[] args) {
        // 使用 ShapeCache 类来获取存储在 Hashtable 中的形状的克隆。
        ShapeCache.loadCache();
        Shape shapeCache1 = ShapeCache.getShape("1");
        Shape shapeCache2 = ShapeCache.getShape("2");
        Shape shapeCache3 = ShapeCache.getShape("3");
        shapeCache1.draw();
        shapeCache2.draw();
        shapeCache3.draw();
    }
}
