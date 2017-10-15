package com.designpattern.bridgepattern;

/**
 * Created by dvWang on 2017/5/25.
 */
public class CoffeeShop extends Shop {
    public CoffeeShop(Supplement supplement) {
        this.supplement=supplement;
    }

    @Override
    public String giveProduct() {
        return supplement.giveSize()+"加"+supplement.giveSupplement();
    }
}
