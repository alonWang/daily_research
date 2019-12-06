package com.github.alonwang.clu.emum;

import java.util.Arrays;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-26 15:07
 **/

public enum CID {
    CONNECT(1), DISCONNECT(2), HOMEPAGE(100), ANSWER(101), INPUT_CHANGE(102);
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