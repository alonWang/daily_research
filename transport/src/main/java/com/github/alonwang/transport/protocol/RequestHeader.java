package com.github.alonwang.transport.protocol;

/**
 * @author alonwang
 * @date 2020/7/22 16:49
 * @detail
 */

public class RequestHeader extends CSMessageHeader {
    public RequestHeader(int moduleId, int commandId) {
        super(moduleId, commandId);
    }
}
