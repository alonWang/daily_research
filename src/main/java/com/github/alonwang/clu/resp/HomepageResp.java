package com.github.alonwang.clu.resp;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-05 16:34
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomepageResp {
    List<Integer> userIds;
    int userId;
    String curIdiom;
}
