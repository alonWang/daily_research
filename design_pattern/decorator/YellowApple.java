package com.designpattern.decorator;

/**
 * Created by dvWang on 2017/5/4.
 */
public class YellowApple extends Decorator{
    public YellowApple(){}
    public YellowApple(Sheep sheep){super(sheep);}
    @Override
    public void flee() {
        sheep.flee();
        waterProof();
    }
    private void waterProof(){
        System.out.println("    水中奔跑");
    }


}
