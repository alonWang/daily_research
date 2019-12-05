package com.github.alonwang.clu.emum;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-26 15:08
 **/

public enum SID {
    SYSTEM_ERROR(0), BUSINESS_EXCEPTION(1), USER_CONNECT(100), HOMEPAGE(101), USER_ANSWER(
            102), NEW_WORD(103), USER_DISCONNECT(104);
    private int value;

    SID(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
