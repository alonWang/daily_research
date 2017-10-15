package com.designpattern.adapter;

/**
 * Created by dvWang on 2017/5/11.
 */
public class Application {
    public static void draw(Shape shape){
        shape.Draw();
    }
    public static void main(String[] args){
        Circle circle=new Circle();
        Line line=new Line();
        Rectangle rectangle=new Rectangle();
        Angle angle=new Angle();
        ShapeAdapter adapter=new ShapeAdapter(angle);

        draw(circle);
        draw(line);
        draw(rectangle);
        draw(adapter);

    }
}
