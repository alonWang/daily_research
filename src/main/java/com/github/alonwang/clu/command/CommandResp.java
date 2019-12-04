package com.github.alonwang.clu.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommandResp {
	private int sid;
	private String body;
}
