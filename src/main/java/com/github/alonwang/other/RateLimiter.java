package com.github.alonwang.other;

public class RateLimiter {
	private final int generateRate;
	private final int capacity;
	private int used;
	private long lastRecoveryTime;

	public RateLimiter(int generateRate, int capacity) {
		this.generateRate = generateRate;
		this.capacity = capacity;
		this.used = 0;
		this.lastRecoveryTime = 0;
	}

	public boolean tryAcquire() {
		long now = System.currentTimeMillis();
		int generated = (int) ((System.currentTimeMillis() - lastRecoveryTime)
				/ 1000.0 * generateRate);
		int nowUsed = used;
		nowUsed = Math.max(nowUsed - generated, 0);

		// 算上这次的
		nowUsed++;
		if (nowUsed <= capacity) {
			used = nowUsed;
			// 仅当生成了才会更新时间
			if (generated > 0) {
				lastRecoveryTime = now;
			}
			return true;
		} else {
			return false;
		}

	}

}
