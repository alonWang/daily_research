package com.github.alonwang.design_pattern.adapter;

/**
 * Created by dvWang on 2017/5/11.
 */
public class Line implements Shape{

    @Override
    public void Draw() {
        System.out.println("画出线");
    }
}
