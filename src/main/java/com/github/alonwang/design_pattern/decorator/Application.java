package com.github.alonwang.design_pattern.decorator;

public class Application {
    public static void exec(Sheep sheep){
        sheep.flee();
    }
    public static void main(String[] args){
        HappySheep happySheep=new HappySheep();
        exec(happySheep);
        RedApple redApple=new RedApple(happySheep);
        exec(redApple);
        YellowApple yellowApple=new YellowApple(redApple);
        exec(yellowApple);
        GreenApple greenApple=new GreenApple(yellowApple);
        exec(greenApple);
    }
}
