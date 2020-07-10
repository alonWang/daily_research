package com.github.alonwang.design_pattern.emum;

/**
 * 抽象处理器,  将基本不会变动的逻辑放在这里,同时允许子类去改变
 *
 * @author alonwang
 * @date 2020/6/20 10:51 上午
 * @detail
 */
public abstract class AbstractFoodProcessor implements FoodProcessor {
    @Override
    public void eat(Food food) {
        System.out.println("吃: " + food.name());
    }

    @Override
    public void steam(Food food) {
        System.out.println("蒸: " + food.name());
    }

    @Override
    public void cooking(Food food) {
        System.out.println("煮: " + food.name());
    }

    @Override
    public void juice(Food food) {
        System.out.println("榨汁: " + food.name());
    }

    @Override
    public void baking(Food food) {
        System.out.println("烘焙: " + food.name());
    }

    /**
     * 食物类型
     *
     * @return
     */
    protected abstract FoodType foodType();
}
