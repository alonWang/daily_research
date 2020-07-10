package com.github.alonwang.design_pattern.emum;

/**
 * @author alonwang
 * @date 2020/6/20 11:38 上午
 * @detail
 */
public class SmallOrange implements Food {
    @Override
    public String name() {
        return "酸橘子";
    }

    @Override
    public FoodType foodType() {
        return FoodType.ORANGE;
    }

}
