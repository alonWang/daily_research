package com.github.alonwang.clu.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-05 18:23
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerCorrectResp {
    private boolean correct;
    private int userId;
    private String word;
}
