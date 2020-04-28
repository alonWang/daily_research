package com.github.alonwang.util.holder;

/**
 * @author alonwang
 * @date 2020/4/28 18:35
 * @description
 * @detail
 */
public interface ValueChangeHolder {
    Number getOld();

    Number getChange();

    Number getNew();

    void setOld(Number oldValue);

    void setChange(Number changeValue);

    void setNew(Number newValue);

    void freeze();
}
