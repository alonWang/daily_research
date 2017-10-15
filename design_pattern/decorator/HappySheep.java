package com.designpattern.decorator;

import java.util.Random;

/**
 * Created by dvWang on 2017/5/4.
 */
public class HappySheep extends Sheep{
    @Override
    public void flee() {
        System.out.println("喜羊羊奔跑并拥有以下Buffer: ");
    }



}
