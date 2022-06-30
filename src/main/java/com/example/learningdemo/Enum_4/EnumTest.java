package com.example.learningdemo.Enum_4;

// 测试使用枚举类
public class EnumTest {
    public static void main(String[] args) {
        Integer apple=1;
        String orange="Orange";
        // 通过code获取其枚举信息
        System.out.println(FruitEnum.getMsgByCode(apple));
        // 通过msg获取其枚举值
        System.out.println(FruitEnum.getCodeByMsg(orange));
        // 使用自定义方法判断是否符合枚举某自定义类型
        if (FruitEnum.myFruit().contains(apple)){
            System.out.println("apple符合MyFruit类型");
        }
    }
}
