package com.github.alonwang.clu.command;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-26 15:08
 **/

public enum SID {
    ERROR(0), NEW_USER_CONNECT(1), HOMEPAGE_INFO(2), USER_ANSWER(3), NEW_WORD(4), USER_DISCONNECT(5);
    private int value;

    SID(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}