package com.github.alonwang.design_pattern.emum;

/**
 * 苹果处理器
 *
 * @author alonwang
 * @date 2020/6/20 11:17 上午
 * @detail
 */
public class AppleFoodProcessor extends AbstractFoodProcessor {
    @Override
    public FoodType foodType() {
        return FoodType.APPLE;
    }

    @Override
    public void baking(Food food) {
        System.out.println(food.name() + "不适合烘焙");
    }
}
