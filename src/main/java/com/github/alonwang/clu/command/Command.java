package com.github.alonwang.clu.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-11-25 14:53
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Command {
    private int id;
    private String body;

}
