package com.github.alonwang.design_pattern.abstractfactory;

/**
 * Created by dvWang on 2017/5/18.
 */
public class FastFoodShop {
    private Hamburg hamburg;
    private Cola cola;
    public void giveFood(FastFoodFactory FFF,float hamburgPrice,String hamburgName,float colaPrice,String colaName){
        hamburg=FFF.createHamburg(hamburgPrice,hamburgName);
        cola=FFF.createCola(colaPrice,colaName);
        showMessage();
    }
    private void showMessage(){
        System.out.println("----快餐订单----");
        System.out.println(hamburg.getName()+" : "+hamburg.getPrice()+"元");
        System.out.println(cola.getName()+" : "+cola.getPrice()+"元");
        System.out.println("-----------------");
    }
}
