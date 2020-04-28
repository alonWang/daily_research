package com.github.alonwang.util.holder;

import com.google.common.base.Preconditions;

/**
 * 添加了变化值必须大于等于0的限制
 * @author alonwang
 * @date 2020/4/28 19:34
 * @description
 * @detail
 */
public class PositiveValueChangeHolder extends DefaultValueChangeHolder {
    @Override
    protected void validateOld(Number oldValue) {
        if (this.newValue != null) {
            Preconditions.checkArgument(this.newValue.doubleValue() >= oldValue.doubleValue());
        }
    }

    @Override
    protected void validateChange(Number changeValue) {
        Preconditions.checkArgument(changeValue.doubleValue() >= 0);
    }

    @Override
    protected void validateNew(Number newValue) {
        if (this.oldValue != null) {
            Preconditions.checkArgument(newValue.doubleValue() >= this.oldValue.doubleValue());
        }
    }
}
