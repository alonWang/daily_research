package com.github.alonwang.design_pattern.strategy;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] arsg) {
        Shopping shop = new Shopping();
        double[] goodsPrices = {10, 25, 33};
        String originalPricesStr = "商品原价： "
                + Arrays.stream(goodsPrices).mapToObj(String::valueOf).collect(Collectors.joining(",", "[", "]"));

        System.out.println("新店开业，满30减5");
        shop.setStrategy(new DeductFiveIfReachThirtyStrategy());
        double num = shop.getMoney(goodsPrices);
        System.out.println(originalPricesStr + "，用户支付的金额为" + num);


        System.out.println("行情不好，五折销售");
        shop.setStrategy(new HalfPriceStrategy());
        num = shop.getMoney(goodsPrices);
        System.out.println(originalPricesStr + "，用户支付的金额为" + num);

        System.out.println("倒闭准备跑路，件件一元");
        shop.setStrategy(new AlmostFreeStrategy());
        num = shop.getMoney(goodsPrices);
        System.out.println(originalPricesStr + "，用户支付的金额为" + num);

    }
}
