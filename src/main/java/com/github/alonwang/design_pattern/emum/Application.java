package com.github.alonwang.design_pattern.emum;

/**
 * @author alonwang
 * @date 2020/6/20 11:19 上午
 * @detail
 */
public class Application {
    public static void main(String[] args) {
        FoodProcessorAgent agent = new FoodProcessorAgent();
        Food bigApple = new BigApple();
        Food smallOrange = new SmallOrange();
        agent.baking(bigApple);
        agent.juice(bigApple);
        agent.eat(bigApple);
        agent.baking(smallOrange);
        agent.juice(smallOrange);
        agent.eat(smallOrange);
    }
}
