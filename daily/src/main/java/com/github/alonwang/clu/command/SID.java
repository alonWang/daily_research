package com.github.alonwang.clu.command;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-26 15:08
 **/

public enum SID {
    ERROR(0), NEW_USER_CONNECT(100), HOMEPAGE_INFO(101), USER_ANSWER(102), NEW_WORD(103), USER_DISCONNECT(104);
    private int value;

    SID(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}