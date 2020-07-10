package com.github.alonwang.design_pattern.abstractfactory;

/**
 * Created by dvWang on 2017/5/18.
 */
public class AbstractFactoryApplication {
    public static void main(String[] args){
        FastFoodShop ffShop=new FastFoodShop();
        ffShop.giveFood(new KFCFactory(),12,"至尊汉堡",4,"百事可乐");
        ffShop.giveFood(new McDonaldsFactory(),14,"巨无霸汉堡",5,"可口可乐");
    }
}
