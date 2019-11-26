package com.github.alonwang.clu.command;

import java.util.Arrays;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-26 15:07
 **/

public enum CID {
    CONNECT(1), ANSWER(2);
    private int value;

    CID(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static CID valueOf(int value) {
        return Arrays.stream(CID.values())
                .filter(v -> v.value() == value).findAny().orElse(null);
    }
}