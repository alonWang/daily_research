package com.github.alonwang.lang;

import java.util.Arrays;

/**
 * @author alonwang
 * @date 2020/6/17 17:22
 * @detail
 */
public enum Food {
    APPLE(1) {
        @Override
        public void eat(long userId) {
            //实际业务可以传递很多参数,做很复杂的逻辑
            System.out.println("user:" + userId + "eat apple,taste sweet");
        }
    }, ORANGE(2) {
        @Override
        public void eat(long userId) {
            System.out.println("user:" + userId + "eat orange,taste sour");

        }
    }, BANANA(3) {
        @Override
        public void eat(long userId) {
            System.out.println("user:" + userId + "eat banana,feel full");
        }
    };

    public abstract void eat(long userId);

    Food(int value) {
        this.value = value;
    }

    private int value;

    public int value() {
        return value;
    }

    public Food valueOf(int value) {
        return Arrays.stream(values())
                .filter(f -> f.value == value)
                .findAny()
                .orElse(null);
    }


}
