package com.example.learningdemo.designModel_13.structureType.adapter;

/**
 * @Author sxc
 * @Description 适配器模式
 * @Date 2022/09/27
 */
public class adapterTest {
    /**
     * 定义：将一个类的接口转换成客户期望的另一个接口，适配器让原本接口不兼容的类可以相互合作。这个定义还好，说适配器的功能就是把一个接口转成另一个接口。
     * 需求:以充电器为实例,手机充电器一般都是5V左右吧，咱天朝的家用交流电压220V，所以手机充电需要一个适配器（降压器）
     */

    public static void main(String[] args) {
        Mobile mobile = new Mobile();
        //手机充电通过V5PowerAdapter适配器将V220Power转化成所需的V5Power
        mobile.inputPower(new V5PowerAdapter(new V220Power()));
    }
}
