package com.github.alonwang.design_pattern.emum;

/**
 * @author alonwang
 * @date 2020/6/20 11:18 上午
 * @detail
 */
public class BigApple implements Food {
    @Override
    public String name() {
        return "大苹果";
    }

    @Override
    public FoodType foodType() {
        return FoodType.APPLE;
    }
}
