package com.designpattern.decorator;

/**
 * Created by dvWang on 2017/5/4.
 */
public class RedApple extends Decorator{
    public RedApple(Sheep sheep){
        super(sheep);
    }


    @Override
    public void flee() {
        sheep.flee();
        shiled();
    }
    private void shiled(){
        System.out.println("    保护罩");
    }
}
