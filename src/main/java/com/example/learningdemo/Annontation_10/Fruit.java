package com.example.learningdemo.Annontation_10;

public class Fruit {

    @FruitName("西瓜")
    private String name;
    @FruitColor(fruitColor= FruitColor.Color.GREEN)
    private String color;
    @FruitProvider(id=1,name="西瓜集团",address="陕西省西安市延安路89号西瓜大厦")
    private String provider;

    public Fruit() {
    }

    public Fruit(String name, String color, String provider) {
        this.name = name;
        this.color = color;
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}
