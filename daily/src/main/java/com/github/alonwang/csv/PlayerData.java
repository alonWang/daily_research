package com.github.alonwang.csv;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author alonwang
 * @date 2021/2/24 3:04 下午
 */
@Data
public class PlayerData {
    @ExcelProperty("角色ID")
    private long playerId;
    @ExcelProperty("账号ID")
    private String account;

}
