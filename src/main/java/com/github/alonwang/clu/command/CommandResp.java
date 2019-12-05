package com.github.alonwang.clu.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommandResp<T> {
	private int sid;
	private T body;

	public static <T> CommandResp<T> newInstance(int sid, T body) {
		return new CommandResp<>(sid, body);
	}
}
