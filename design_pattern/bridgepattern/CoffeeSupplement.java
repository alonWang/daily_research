package com.designpattern.bridgepattern;

/**
 * Created by dvWang on 2017/5/25.
 */
public class CoffeeSupplement implements Supplement {
    private String supplement;
    private String size;
    public CoffeeSupplement(String supplement,String size) {
        this.supplement = supplement;
        this.size=size;
    }

    @Override
    public String giveSupplement() {
        return supplement;
    }

    @Override
    public String giveSize() {
        return size;
    }
}
