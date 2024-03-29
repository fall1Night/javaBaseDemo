package com.example.learningdemo.designModel_13.careateType.builder;

/**
 * @Author sxc
 * @Description 建造者模式
 * @Date 2022/09/27
 */
public class BuilderTest {
    /**
     * 建造模式是对象的创建模式。建造模式可以将一个产品的内部表象（internal representation）与产品的生产过程分割开来，
     * 从而可以使一个建造过程生成具有不同的内部表象的产品对象。
     *
     * <p>
     * Builder 类是关键，然后定义一个Builder实现类，再之后就是处理实现类的逻辑。
     * <p>
     * 优点：
     * 1. 首先，建造者模式的封装性很好。使用建造者模式可以有效的封装变化，在使用建造者模式的场景中，
     * 一般产品类和建造者类是比较稳定的，因此，将主要的业务逻辑封装在导演类中对整体而言可以取得比较好的稳定性。
     * 2. 其次，建造者模式很容易进行扩展。如果有新的需求，通过实现一个新的建造者类就可以完成，
     * 基本上不用修改之前已经测试通过的代码，因此也就不会对原有功能引入风险。
     * 总结：
     * 建造者模式与工厂模式类似，他们都是建造者模式，适用的场景也很相似。
     * 一般来说，如果产品的建造很复杂，那么请用工厂模式；如果产品的建造更复杂，那么请用建造者模式。
     *
     * 需求:用户去汽车店购买汽车,汽车店根据每个用户的需求提取对应汽车
     */
    public static void main(String[] args) {
        // 店长
        Director director = new Director();
        // 得到宝马汽车，内部实现提取宝马汽车的详情操作
        Product product = director.getBProduct();
        product.showProduct();
    }


}
