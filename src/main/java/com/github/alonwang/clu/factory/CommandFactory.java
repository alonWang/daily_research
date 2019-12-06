package com.github.alonwang.clu.factory;

import com.github.alonwang.clu.command.Command;
import com.github.alonwang.clu.command.impl.AnswerCommand;
import com.github.alonwang.clu.command.impl.ConnectCommand;
import com.github.alonwang.clu.command.impl.DisconnectCommand;
import com.github.alonwang.clu.command.impl.HomepageCommand;
import com.github.alonwang.clu.command.impl.InputChangeCommand;
import com.github.alonwang.clu.emum.CID;
import com.github.alonwang.clu.exception.BusinessException;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-05 15:40
 **/
public class CommandFactory {

    public static Command createCommand(CID cidEnum, String body) {
        switch (cidEnum) {
            case CONNECT:
                return new ConnectCommand();
            case DISCONNECT:
                return new DisconnectCommand();
            case HOMEPAGE:
                return new HomepageCommand();
            case ANSWER:
                return new AnswerCommand(body);
            case INPUT_CHANGE:
                return new InputChangeCommand(body);
            default:
                throw new BusinessException("cid illegal");
        }
    }
}
