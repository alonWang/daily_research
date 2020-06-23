package com.github.alonwang.design_pattern.emum;

/**
 * 橘子处理器
 *
 * @author alonwang
 * @date 2020/6/20 11:39 上午
 * @detail
 */
public class OrangeFoodProcessor extends AbstractFoodProcessor {
    /**
     * 橘子专用榨汁机
     */
    private Object juicer = new Object();

    @Override
    public FoodType foodType() {
        return FoodType.ORANGE;
    }

    @Override
    public void juice(Food food) {
        //使用特殊的榨汁机给橘子榨汁
        // juicer.xxx(food);
        System.out.println(food.name() + " 适合榨汁");
    }
}
