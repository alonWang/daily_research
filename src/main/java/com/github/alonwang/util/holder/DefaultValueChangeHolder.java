package com.github.alonwang.util.holder;

import com.google.common.base.Preconditions;

/**
 * ValueChangeHolder默认实现
 *
 * @author alonwang
 * @date 2020/4/28 18:37
 * @detail 允许子类通过实现validateXXX增加验证
 */
public class DefaultValueChangeHolder implements ValueChangeHolder {
    protected Number oldValue;
    protected Number changeValue;
    protected Number newValue;
    private boolean calculated;

    @Override
    public Number getOld() {
        freeze();
        return oldValue;
    }

    @Override
    public void setOld(Number oldValue) {
        allowModifyOrDie();
        validateOld(oldValue);
        this.oldValue = oldValue;
    }

    @Override
    public Number getChange() {
        freeze();

        return changeValue;
    }

    @Override
    public void setChange(Number changeValue) {
        allowModifyOrDie();
        validateChange(changeValue);
        this.changeValue = changeValue;
    }

    @Override
    public Number getNew() {
        freeze();
        return newValue;
    }

    @Override
    public void setNew(Number newValue) {
        allowModifyOrDie();
        validateNew(newValue);
        this.newValue = newValue;
    }

    @Override
    public void freeze() {
        if (!calculated) {
            calculate();
            calculated = true;
        }
    }

    protected void validateOld(Number oldValue) {
    }


    protected void validateChange(Number changeValue) {
    }

    protected void validateNew(Number newValue) {
    }


    private void allowModifyOrDie() {
        Preconditions.checkArgument(!calculated, "Can't set after freeze");
    }

    @Override
    public String toString() {
        return "{" +
                "oldValue=" + oldValue +
                ", changeValue=" + changeValue +
                ", newValue=" + newValue +
                ", calculated=" + calculated +
                '}';
    }

    private void calculate() {
        if (oldValue != null && changeValue != null && newValue != null) {
            throw new IllegalStateException("Values shouldn't all present before calculate");
        }
        if (oldValue != null && changeValue != null) {
            newValue = oldValue.doubleValue() + changeValue.doubleValue();
            calculated = true;
            return;
        }
        if (oldValue != null && newValue != null) {
            changeValue = newValue.doubleValue() - oldValue.doubleValue();
            calculated = true;
            return;
        }
        if (changeValue != null && newValue != null) {
            oldValue = newValue.doubleValue() - changeValue.doubleValue();
            calculated = true;
            return;
        }
        if (oldValue == null || changeValue == null || newValue == null) {
            throw new IllegalStateException("values should all present after calculate");
        }


    }
}
