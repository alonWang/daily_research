package com.github.alonwang.design_pattern.emum;

import com.google.common.base.Preconditions;

import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 代理processor,隐藏真实的processor,使调用更为方便
 *
 * @author alonwang
 * @date 2020/6/20 10:55 上午
 * @detail
 */
public class FoodProcessorAgent implements FoodProcessor {
    private Map<FoodType, FoodProcessor> processors;

    public FoodProcessorAgent() {
        processors = new HashMap<>();
        Reflections reflections = new Reflections(FoodProcessorAgent.class.getPackage().getName());
        Set<Class<? extends AbstractFoodProcessor>> classes = reflections.getSubTypesOf(AbstractFoodProcessor.class);
        for (Class<? extends AbstractFoodProcessor> processorClass : classes) {
            if (Modifier.isAbstract(processorClass.getModifiers())) {
                continue;
            }
            try {
                AbstractFoodProcessor processor = processorClass.newInstance();
                processors.put(processor.foodType(), processor);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        processors = Collections.unmodifiableMap(processors);

    }

    public FoodProcessor get(Food food) {
        Preconditions.checkArgument(food != null && food.foodType() != null);
        FoodProcessor processor = processors.get(food.foodType());
        Preconditions.checkNotNull(processor);
        return processor;
    }

    @Override
    public void eat(Food food) {
        get(food).eat(food);
    }

    @Override
    public void steam(Food food) {
        get(food).steam(food);
    }

    @Override
    public void cooking(Food food) {
        get(food).cooking(food);
    }

    @Override
    public void juice(Food food) {
        get(food).juice(food);
    }

    @Override
    public void baking(Food food) {
        get(food).baking(food);
    }

}
