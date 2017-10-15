package com.designpattern.decorator;

/**
 * Created by dvWang on 2017/5/4.
 */
public class GreenApple extends Decorator{
    public GreenApple(){}
    public GreenApple(Sheep sheep){super(sheep);}
    @Override
    public void flee() {
        sheep.flee();
        speedUp();
    }
    private void speedUp(){
        System.out.println("        加速");
    }


}
