package com.github.alonwang.design_pattern.adapter;

/**
 * Created by dvWang on 2017/5/11.
 */
public class ShapeAdapter implements Shape{
        private Angle angle;
        public ShapeAdapter(Angle angle){
            this.angle=angle;
        }
    @Override
    public void Draw() {
        angle.DrawAngle();
    }
}
