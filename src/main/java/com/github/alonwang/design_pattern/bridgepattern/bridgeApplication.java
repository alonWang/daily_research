package com.github.alonwang.design_pattern.bridgepattern;

/**
 * Created by dvWang on 2017/5/25.
 */
public class bridgeApplication {
    public static void main(String[] args){
        Supplement supplement=new CoffeeSupplement("糖","大杯");
        Shop shop=new CoffeeShop(supplement);
        System.out.print("您好： 咖啡"+shop.giveProduct());
    }
}
