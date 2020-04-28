package com.github.alonwang.util.holder;

/**
 * 数值变化记录器,适用于追踪数值变化场景
 *
 * @author alonwang
 * @date 2020/4/28 18:35
 * @detail
 * 功能
 * * 设置 旧值,变化值,新值 中的任意两个,即可自动计算另外一个
 * * 冻结后自动计算数值,之后不允许再修改
 * * 第一次获取数值时自动冻结
 * * 允许自定义数值验证,如变化值必须大于等于0
 */
public interface ValueChangeHolder {
    /**
     * 获取旧值
     *
     * @return
     */
    Number getOld();

    /**
     * 获取变化值
     *
     * @return
     */
    Number getChange();

    /**
     * 获取新值
     *
     * @return
     */
    Number getNew();

    /**
     * 设置旧值
     *
     * @param oldValue
     */
    void setOld(Number oldValue);

    /**
     * 设置变化值
     *
     * @param changeValue
     */
    void setChange(Number changeValue);

    /**
     * 设置新值
     *
     * @param newValue
     */
    void setNew(Number newValue);

    /**
     * 冻结,冻结后不允许修改
     */
    void freeze();
}
