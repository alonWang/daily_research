package com.github.alonwang.design_pattern.emum;

/**
 * @author alonwang
 * @date 2020/6/20 10:34 上午
 * @detail
 */
public interface FoodProcessor {
    /**
     * 直接吃
     *
     * @param food
     */
    void eat(Food food);

    /**
     * 蒸
     *
     * @param food
     */
    void steam(Food food);

    /**
     * 煮
     *
     * @param food
     */
    void cooking(Food food);

    /**
     * 榨汁
     *
     * @param food
     */
    void juice(Food food);

    /**
     * 烘焙
     *
     * @param food
     */
    void baking(Food food);


}
