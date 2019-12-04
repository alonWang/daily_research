package com.github.alonwang.clu.command;

public abstract class AbstractCommand implements Command {
	private CommandParam param;

	public AbstractCommand(CommandParam param) {
		this.param = param;
	}

	public void execute() {

	}

}
