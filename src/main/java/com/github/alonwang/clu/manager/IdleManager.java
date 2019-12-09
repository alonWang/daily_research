package com.github.alonwang.clu.manager;

import com.github.alonwang.clu.command.CommandResp;
import com.github.alonwang.clu.emum.SID;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: alonwang
 * @create: 2019-12-06 18:18
 **/
public class IdleManager {
	private static final ScheduledExecutorService executorService = Executors
			.newSingleThreadScheduledExecutor();
	private static volatile boolean idle;

	public static void init() {
		// 定期更换成语
		executorService.scheduleAtFixedRate(() -> {
			if (IdleManager.isIdle()) {
				IdiomManager.next(IdiomManager.current());
				GroupManager.channelGroup().writeAndFlush(
						CommandResp.newInstance(SID.NEW_WORD.value(),
								IdiomManager.current()));
			} else {
				IdleManager.idle();
			}
		}, 30, 30, TimeUnit.SECONDS);
	}

	public static boolean isIdle() {
		return idle;
	}

	public static void active() {
		idle = false;
	}

	public static void idle() {
		idle = true;
	}
}
