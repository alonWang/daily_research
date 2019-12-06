package com.github.alonwang.clu.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-06 09:06
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInputChangeResp {
    private int userId;
    private String word;
}
