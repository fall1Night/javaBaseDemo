package com.example.learningdemo.designModel_13.structureType.flyWeight;

import java.util.HashMap;

public class ShapeFactory {

    private static final HashMap<String, Shape> circleMap = new HashMap<String, Shape>();

    public static Shape getShape(String color) {
        Shape shape = circleMap.get(color);
        if (shape == null) {
            shape = new Circle(color);
            circleMap.put(color, shape);
            System.out.println("Creating circle of color : " + color);
        }
        return shape;
    }

}